package com.podzirey.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> extends AbstractList<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private T[] array;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        growIfNoCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void growIfNoCapacity() {
        if (size == array.length) {
            double newCapacity = array.length * 1.5 + 1;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[(int) newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public T remove(int index) {
        validateExistingIndex(index);
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
        validateExistingIndex(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        validateExistingIndex(index);
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
    public int indexOf(T value) {
        if (Objects.isNull(value)) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    continue;
                }
                if (array[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        int result = -1;
        if (Objects.isNull(value)) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int index = 0;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element is the List");
            }
            T value = array[index];
            index++;
            canRemove = true;
            return value;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Already removed");
            }
            ArrayList.this.remove(index - 1);
            canRemove = false;
            index--;
        }
    }
}
