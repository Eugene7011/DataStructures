package com.podzirey.datastructures.stack;

import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {
    private final static int DEFAULT_INITIAL_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayStack() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        this.array = (T[]) new Object[initialCapacity];
    }

    @Override
    public void push(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    private void ensureCapacity() {
        if (array.length == size) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new ArrayStack[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T result = array[size - 1];
        size--;
        return result;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[size - 1];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            T valueInStack = array[i];
            if (value.equals(valueInStack)) {
                return true;
            }
        }
        return false;
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
    public void clear() {
        for (Object o : array) {
            o = null;
        }
        size = 0;
    }
}
