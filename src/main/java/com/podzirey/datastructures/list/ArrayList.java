package com.podzirey.datastructures.list;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T>, Iterable<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private T[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    private void growIfNoCapacity() {
        if (size == array.length) {
            double newCapacity = array.length * 1.5 + 1;
            T[] newArray = (T[]) new Object[(int) newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0] - [size]");
        }
        growIfNoCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void checkIfIndexIsInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index should be in range [0] - [size - 1]");
        }
    }

    @Override
    public T remove(int index) {
        checkIfIndexIsInBounds(index);
        T removed = array[index];
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T get(int index) {
        checkIfIndexIsInBounds(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        checkIfIndexIsInBounds(index);
        T old = array[index];
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
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
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

        for (T value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T> {
        private int index = 0;
        boolean canRemove;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T value = array[index];
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
