package com.podzirey.datastructures.list;

import java.util.StringJoiner;

public class ArrayList implements List {
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
        isEnoughSpaceInArray();
        array[size] = value;
        size++;
    }

    private void isEnoughSpaceInArray() {
        if (size == array.length) {
            double newCapacity = size * 1.5;
            Object[] newArray = new Object[(int) newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;

        }
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        isEnoughSpaceInArray();

        System.arraycopy(array, index, array, index + 1, size - index);
        size++;
        array[index] = value;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Object removed = array[index];
        if (index == size - 1) {
            array[index] = null;
            size--;
        } else {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            array[size - 1] = null;
        }
        return removed;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (value == null) {
            throw new IllegalArgumentException("Object value can't be null!");
        }
        Object old = array[index];
        array[index] = value;
        return old;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Array is already empty!");
        }
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
        if (value == null) {
            throw new IllegalArgumentException("Object value can't be null!");
        }
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Object value can't be null!");
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Object value can't be null!");
        }
        if (isEmpty()) {
            throw new IllegalArgumentException("Array is empty");
        }
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value))
                result = i;
        }
        return result;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Array is empty");
        }
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(get(i).toString());
        }
        return stringJoiner.toString();
    }

}
