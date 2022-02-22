package com.podzirey.datastructures.list;

import java.util.StringJoiner;

public abstract class AbstractList<T> implements List<T>,Iterable<T>{
    protected int size;

    public AbstractList() {

    }

    @Override
    public void add(T value) {
        add(value, this.size);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        for (T value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
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

    protected void checkIfIndexIsInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is " + index + " but should be in range [0," + (size - 1) + "]");
        }
    }
}
