package com.podzirey.datastructures.map;

import java.util.ArrayList;
import java.util.List;

public class HashMap implements Map {
    private static final int INITIAL_CAPACITY = 5;
    private HashMap map;
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
        ArrayList<Entry> bucket = buckets[calculateIndex(key)];
        Entry newEntry = new Entry(key, value);
        int index = indexOfEntryInBucketByKey(key);
        Object result;
        if (index == -1) {
            increaseHashMapCapacityIfNeeded();
            bucket.add(newEntry);
            result = null;
            size++;
        } else {
            Entry oldEntry = bucket.get(index);
            result = oldEntry.value;
            oldEntry.value = value;
        }
        return result;
    }

    private void increaseHashMapCapacityIfNeeded() {
        if (buckets.length * 0.75 < size) {
            int doubleCapacity = buckets.length * 2;
            HashMap increasedHashMap = new HashMap(doubleCapacity);
            increasedHashMap.putAll(this);
            map = increasedHashMap;
        }
    }

    private int indexOfEntryInBucketByKey(Object key) {
        ArrayList<Entry> bucket = buckets[calculateIndex(key)];
        List<Object> keysInBucket = new ArrayList<>(bucket.size());
        for (Entry entry : bucket) {
            keysInBucket.add(entry.key);
        }
        return keysInBucket.indexOf(key);
    }

    private int calculateIndex(Object key) {
        return key.hashCode() % buckets.length;
    }

    @Override
    public Object get(Object key) {
        ArrayList<Entry> bucket = buckets[calculateIndex(key)];
        int index = indexOfEntryInBucketByKey(key);
        if (index != -1) {
            return (bucket.get(index)).value;
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        ArrayList<Entry> bucket = buckets[calculateIndex(key)];
        int index = indexOfEntryInBucketByKey(key);
        if (index != -1) {
            Object result = bucket.remove(index);
            size--;
            return ((Entry) result).value;
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
        return indexOfEntryInBucketByKey(key) != -1;
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
    public List<Object> key() {
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
