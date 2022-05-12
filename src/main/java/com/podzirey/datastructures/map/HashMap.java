package com.podzirey.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 5;
    private static final double LOAD_LEVEL = 0.75;
    private static final double INCREASE_STEP = 2;

    private ArrayList<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.buckets = new ArrayList[capacity];
    }

    @Override
    public V put(K key, V value) {
        increaseHashMapCapacityIfNeeded();
        List<Entry<K, V>> bucket = buckets[calculateIndex(key)];
        if (bucket == null) {
            buckets[calculateIndex(key)] = new ArrayList<>(1);
            buckets[calculateIndex(key)].add(new Entry<>(key, value));
            size++;
            return null;
        }
        if (containsKey(key)) {
            Entry<K, V> oldEntry = getEntry(key);
            V result = Objects.requireNonNull(oldEntry).value;
            oldEntry.value = value;
            return result;
        }
        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        if (isEmpty()) {
            return null;
        }
        List<Entry<K, V>> bucket = buckets[calculateIndex(key)];
        if (bucket == null) {
            return null;
        }
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (isEmpty()) {
            return null;
        }
        List<Entry<K, V>> bucket = buckets[calculateIndex(key)];
        if (bucket == null) {
            return null;
        }
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                V result = entry.value;
                bucket.remove(entry);
                size--;
                return result;
            }
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
        List<Entry<K, V>> bucket = buckets[calculateIndex(key)];
        if (bucket == null) {
            return false;
        }
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return true;
            }
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
        for (ArrayList<Entry<K, V>> bucket : addedMap.buckets) {
            if (bucket == null) {
                continue;
            }
            for (Entry<K, V> entry : bucket) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public List<K> keys() {
        if (isEmpty()) {
            return null;
        }
        List<K> keys = new ArrayList<>(size);
        for (ArrayList<Entry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (Entry<K, V> entry : bucket) {
                keys.add(entry.key);
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        if (isEmpty()) {
            return null;
        }
        List<V> values = new ArrayList<>(size);
        for (ArrayList<Entry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (Entry<K, V> entry : bucket) {
                values.add(entry.value);
            }
        }
        return values;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {

        private int currentEntryCount;
        private int currentBucketIndex;
        private int currentEntryIndexInBucket;
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
            Entry<K, V> currentEntry;
            for (int i = currentBucketIndex; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    currentBucketIndex = i;
                    if (currentEntryIndexInBucket < buckets[i].size()) {
                        currentEntry = buckets[i].get(currentEntryIndexInBucket);
                        currentEntryIndexInBucket++;
                        currentEntryCount++;
                        canRemove = true;
                        return currentEntry;
                    } else {
                        currentBucketIndex++;
                        currentEntryIndexInBucket = 0;
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
            Entry<K, V> entryToBeDeleted = buckets[currentBucketIndex].get(currentEntryIndexInBucket - 1);
            HashMap.this.remove(entryToBeDeleted.key);
            canRemove = false;
            currentEntryCount--;
            currentEntryIndexInBucket--;
        }
    }

    private Entry<K, V> getEntry(K key) {
        List<Entry<K, V>> bucket = buckets[calculateIndex(key)];
        if (bucket == null) {
            return null;
        }
        for (Entry<K, V> entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return entry;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void increaseHashMapCapacityIfNeeded() {
        if (buckets.length * LOAD_LEVEL < size) {
            int capacity = (int) (buckets.length * INCREASE_STEP);
            ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[capacity];

            for (Map.Entry<K, V> currentEntry : this) {
                innerPut(newBuckets, currentEntry);
            }
            buckets = newBuckets;
        }
    }

    private void innerPut(ArrayList<Entry<K, V>>[] newBuckets, Map.Entry<K, V> currentEntry) {
        int bucketIndex = calculateIndex(currentEntry.getKey(), newBuckets.length);
        if (newBuckets[bucketIndex] == null) {
            newBuckets[bucketIndex] = new ArrayList<>(1);
            newBuckets[bucketIndex].add(new Entry<>(currentEntry.getKey(), currentEntry.getValue()));
        }

        newBuckets[bucketIndex].add(new Entry<>(currentEntry.getKey(), currentEntry.getValue()));
    }

    int calculateIndex(K key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        if (hashCode == Integer.MIN_VALUE) {
            return 0;
        }
        return Math.abs(hashCode) % buckets.length;
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
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
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