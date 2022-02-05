package com.podzirey.datastructures.list;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayList implements List, Iterable {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private Object[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int capacity) {
        this.array = new Object[capacity];
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    private void growIfNoCapacity() {
        if (size == array.length) {
            double newCapacity = array.length * 1.5 + 1;
            Object[] newArray = new Object[(int) newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0] - [size]");
        }
        growIfNoCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    public void checkIfIndexIsInBounds(int index){
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index should be in range [0] - [size - 1]");
        }
    }

    @Override
    public Object remove(int index) {
        checkIfIndexIsInBounds(index);
        Object removed = array[index];
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public Object get(int index) {
        checkIfIndexIsInBounds(index);
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        checkIfIndexIsInBounds(index);
        Object old = array[index];
        array[index] = value;
        return old;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
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
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value))
                result = i;
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        for (Object value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator {
        private int index = 0;
        boolean canRemove;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Object next() {
            Object value = array[index];
            index++;
            canRemove = true;
            return value;
        }

        @Override
        public void remove() {
            if (canRemove) {
                ArrayList.this.remove(index - 1);
            } else {
                throw new IllegalArgumentException("Already removed");
            }
            canRemove = false;
            index--;
        }
    }
}
