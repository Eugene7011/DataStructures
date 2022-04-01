package com.podzirey.datastructures.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashMap implements Map {
    private static final int INITIAL_CAPACITY = 5;
    private ArrayList<Entry>[] buckets = new ArrayList[INITIAL_CAPACITY];
    private int size;

    public HashMap() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public HashMap(int doubleCapacity) {
        this.buckets = new ArrayList[doubleCapacity];
        for (int i = 0; i < doubleCapacity; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public Object put(Object key, Object value) {
        List<Entry> bucket = buckets[calculateIndex(key)];
        Entry newEntry = new Entry(key, value);
        Object result;
        if (!containsKey(key)) {
            increaseHashMapCapacityIfNeeded();
            bucket.add(newEntry);
            result = null;
            size++;
        } else {
            Entry oldEntry = getEntry(key);
            result = oldEntry.value;
            oldEntry.value = value;
        }
        return result;
    }

    private Entry getEntry(Object key) {
        List<Entry> bucket = buckets[calculateIndex(key)];
        for (Entry entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return entry;
            }
        }
        return null;
    }

    private void increaseHashMapCapacityIfNeeded() {
        if (buckets.length * 0.75 < size) {
            int doubleCapacity = buckets.length * 2;
            ArrayList<Entry>[] newBuckets = new ArrayList[doubleCapacity];

            for (int i = 0; i < newBuckets.length; i++) {
                newBuckets[i] = new ArrayList<>();
            }

            for (ArrayList<Entry> bucket : buckets) {
                for (Entry entry : bucket) {
                    List<Entry> newBucket = newBuckets[(entry.key).hashCode() % newBuckets.length];
                    newBucket.add(entry);
                }
            }
            buckets = newBuckets;
        }
    }

    int calculateIndex(Object key) {
        return key.hashCode() % buckets.length;
    }

    @Override
    public Object get(Object key) {
        List<Entry> bucket = buckets[calculateIndex(key)];
        for (Entry entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        List<Entry> bucket = buckets[calculateIndex(key)];
        for (Entry entry : bucket) {
            if (Objects.equals(entry.key, key)) {
                Object result = entry.value;
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
    public boolean containsKey(Object key) {
        return keys().contains(key);
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        Object result = get(key);
        if (result == null) {
            put(key, value);
        }
        return result;
    }

    @Override
    public void putAll(HashMap addedMap) {
        for (ArrayList<Entry> bucket : addedMap.buckets) {
            for (Entry entry : bucket) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public List<Object> keys() {
        List<Object> keys = new ArrayList<>(size);
        for (ArrayList<Entry> bucket : buckets) {
            for (Entry entry : bucket) {
                keys.add(entry.key);
            }
        }
        return keys;
    }

    @Override
    public List<Object> values() {
        List<Object> values = new ArrayList<>(size);
        for (ArrayList<Entry> bucket : buckets) {
            for (Entry entry : bucket) {
                values.add(entry.value);
            }
        }
        return values;
    }

    private static class Entry {
        private Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
