package com.podzirey.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_LEVEL = 0.75;
    private static final double INCREASE_STEP = 2;

    private Entry<K, V>[] buckets;
    private int size;

    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.buckets = new Entry[capacity];
    }

    @Override
    public V put(K key, V value) {
        increaseHashMapCapacityIfNeeded();
        int hash = getHash(key);

        Entry<K, V> newEntry = new Entry<>(hash, key, value, null);
        Entry<K, V> currentEntry = buckets[calculateIndex(key)];
        if (buckets[calculateIndex(key)] == null) {
            buckets[calculateIndex(key)] = newEntry;
            size++;
            return null;
        }

        boolean updated = false;

        V result = null;

        while (currentEntry != null) {
            if (currentEntry.hash == hash && Objects.equals(currentEntry.key, key)) {
                result = currentEntry.value;
                currentEntry.value = value;
                updated = true;
            }

            currentEntry = currentEntry.next;
        }

        if (!updated) {
            currentEntry.next = newEntry;
            size++;
            return null;
        }

        return result;
    }

    @Override
    public V get(K key) {
        if (isEmpty()) {
            return null;
        }
        Entry<K, V> currentEntry = buckets[calculateIndex(key)];
        if (currentEntry == null) {
            return null;
        }
        int hash = getHash(key);

        while (currentEntry != null) {
            if (currentEntry.hash == hash && Objects.equals(currentEntry.key, key)) {
                return currentEntry.value;
            }
            currentEntry = currentEntry.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (isEmpty()) {
            return null;
        }
        Entry<K, V> entryToBeDeleted = buckets[calculateIndex(key)];
        int hash = getHash(key);

        int count = 0;
        boolean contains = false;

        while (entryToBeDeleted != null) {
            if (entryToBeDeleted.hash == hash && Objects.equals(entryToBeDeleted.key, key)) {

                contains = true;
                //return result;
                break;
            }
            entryToBeDeleted = entryToBeDeleted.next;
            count++;
        }
        if (contains) {
            entryToBeDeleted = getEntry(key);
            V result = entryToBeDeleted.value;

            if (buckets[calculateIndex(key)].next == null) {
                buckets[calculateIndex(key)] = null;
                size--;
                return result;
            }

            if (count == 0) {
                buckets[calculateIndex(key)] = buckets[calculateIndex(key)].next;
            } else {
                for (int i = 1; i < count; i++) {
                    buckets[calculateIndex(key)] = buckets[calculateIndex(key)].next;
                }
                buckets[calculateIndex(key)] = buckets[calculateIndex(key)].next.next;
                entryToBeDeleted = null;
            }

            size--;
            return result;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (isEmpty()) {
            return false;
        }
        Entry<K, V> currentEntry = buckets[calculateIndex(key)];

        if (currentEntry == null) {
            return false;
        }
        int hash = getHash(key);
        while (currentEntry != null) {

            if (currentEntry.hash == hash && Objects.equals(currentEntry.key, key)) {
                return true;
            }
            currentEntry = currentEntry.next;
        }
        return false;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (isEmpty()) {
            return null;
        }
        V result = get(key);
        if (result == null) {
            put(key, value);
        }
        return result;
    }

    @Override
    public void putAll(HashMap<K, V> addedMap) {
        for (Entry<K, V> bucket : addedMap.buckets) {
            if (bucket == null) {
                continue;
            }
            while (bucket != null) {
                put(bucket.key, bucket.value);
                bucket = bucket.next;
            }
            this.iterator();
        }
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {

        private Entry<K, V> currentEntry;
        private int currentBucketIndex;
        private int currentEntryCount;
        private int currentEntryAmountInBucket;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return currentEntryCount < size;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element");
            }

            for (int i = currentBucketIndex; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    currentBucketIndex = i;
                    currentEntry = buckets[i];
                    Entry<K, V> value = currentEntry;
                    if (currentEntryAmountInBucket == 0) {
                        currentEntryCount++;
                        currentEntryAmountInBucket++;
                        canRemove = true;
                        return value;
                    }
                    for (int j = 0; j < currentEntryAmountInBucket - 1; j++) {
                        currentEntry = currentEntry.next;
                    }

                    if (currentEntry.next != null) {
                        currentEntry = currentEntry.next;
                        currentEntryCount++;
                        currentEntryAmountInBucket++;
                        canRemove = true;
                        return currentEntry;
                    } else {
                        currentBucketIndex++;
                        currentEntryAmountInBucket = 0;
                    }
                }
            }
            return null;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Method next() should be called before remove");
            }
            Entry<K, V> entryToBeDeleted = buckets[currentBucketIndex];
            if (currentEntryAmountInBucket > 1) {
                for (int i = 1; i < currentEntryCount; i++) {
                    entryToBeDeleted = entryToBeDeleted.next;
                }
            }

            HashMap.this.remove(entryToBeDeleted.key);
            canRemove = false;
            currentEntryCount--;
        }
    }

    private Entry<K, V> getEntry(K key) {
        Entry<K, V> entry = buckets[calculateIndex(key)];
        if (entry == null) {
            return null;
        }
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void increaseHashMapCapacityIfNeeded() {
        if (buckets.length * LOAD_LEVEL < size) {
            int capacity = (int) (buckets.length * INCREASE_STEP);
            buckets = new Entry[capacity];
            for (Map.Entry<K, V> kvEntry : this) {
                innerPut(buckets, kvEntry);
            }
        }
    }

    private void innerPut(Entry<K, V>[] buckets, Map.Entry<K, V> currentEntry) {
        int bucketIndex = calculateIndex(currentEntry.getKey(), buckets.length);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new Entry<>(currentEntry.getKey().hashCode(), currentEntry.getKey(), currentEntry.getValue(), null);
        }
        buckets[bucketIndex].next = new Entry<>(currentEntry.getKey().hashCode(), currentEntry.getKey(), currentEntry.getValue(), null);
    }

    private int getHash(K key) {
        int hash;
        if (key == null) {
            hash = 0;
        } else {
            hash = key.hashCode();
        }
        return hash;
    }

    int calculateIndex(K key) {
        if (key == null) {
            return 0;
        }
        if (key.hashCode() == Integer.MIN_VALUE) {
            return 0;
        }
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private int calculateIndex(K key, int length) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        if (hashCode == Integer.MIN_VALUE) {
            return 0;
        }
        return Math.abs(hashCode) % length;
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }
    }
}