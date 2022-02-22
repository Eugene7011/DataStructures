package com.podzirey.datastructures.queue;

import java.util.StringJoiner;

public class ArrayQueue implements Queue {
    private int size;
    private Object[] array;

    public ArrayQueue() {
        array = new Object[7];
    }

    public ArrayQueue(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    @Override
    public void enqueue(Object value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    private void ensureCapacity() {
        if (array.length == size) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is Empty!");
        }
        Object result = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        size--;
        return result;
    }


    @Override
    public Object peek() {
        return array[0];
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
        for (int i = 0; i < size; i++) {
            Object valueInQueue = array[i];
            if (value.equals(valueInQueue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (Object o : array) {
            o = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        for (Object value : array) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }
}