package com.podzirey.datastructures.list;

import java.util.StringJoiner;

public abstract class AbstractList<T> implements List<T> {
    protected int size;

    @Override
    public void add(T value) {
        add(value, size);
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
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    protected void validateExistingIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is " + index + " but should be in range [0," + (size - 1) + "]");
        }
    }

    protected void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            if (size == 0) {
                throw new IndexOutOfBoundsException("Index is " + index + " but should be [0] because list is empty now");
            }
            throw new IndexOutOfBoundsException("Index is " + index + " but should be in range [0," + size + "]");
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        for (T value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }
}
