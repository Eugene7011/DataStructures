package com.podzirey.datastructures.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
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
