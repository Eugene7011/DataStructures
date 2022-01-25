package com.podzirey.datastructures.queue;

public class ArrayQueue implements Queue{
    private int size;
    private Object[] array;

    public ArrayQueue(){
        array = new Object[7];
    }

    public ArrayQueue(int initialCapacity){
        array = new Object[initialCapacity];
    }

    @Override
    public void enqueue(Object value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    private void ensureCapacity(){
        if(array.length == size){
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    @Override
    public Object dequeue() {
        if(isEmpty()){
            throw new IllegalStateException("Queue is Empty!");
        }
        Object result = array[0];
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
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
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            Object valueInQueue = array[i];
            if(value.equals(valueInQueue)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString(){
        String result = "[" ;
        for (int i = 0; i < size; i++) {
            String temp = String.valueOf(array[i]);
            result = result + temp;
            if(i < size -1){
                result = result + ", ";
            }
        }

        return result + "]";
    }
}