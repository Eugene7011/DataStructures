package com.podzirey.datastructures.map;

import java.util.*;

public class HashMap implements Map {
    private static final int INITIAL_CAPACITY = 5;
    private ArrayList<Entry>[] buckets = new ArrayList[INITIAL_CAPACITY];
    private int size;

    private int calculateIndex(Object key) {
        return key.hashCode() % buckets.length;
    }

    @Override
    public Object put(Object key, Object value) {
        Object oldValue = null;
        boolean updated = false;
        int index = calculateIndex(key);

        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                oldValue = entry.value;
                entry.value = value;
                updated = true;
            }
        }

        if (!updated) {
            buckets[index].add(new Entry(key, value));
            size++;
            return null;
        }
        return oldValue;
    }

    @Override
    public Object get(Object key) {
        int index = calculateIndex(key);
        if (buckets[index] == null) {
            return null;
        }
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        int index = calculateIndex(key);
        if (buckets[index] == null) {
            return null;
        }
        Object oldValue;
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                oldValue = entry.value;
                entry.value = null;
                size--;
                return oldValue;
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
        int index = calculateIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }

        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
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
