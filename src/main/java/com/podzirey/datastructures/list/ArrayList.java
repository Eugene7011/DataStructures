package com.podzirey.datastructures.list;

import javax.management.ValueExp;

public class ArrayList implements  List{
    private Object[] array;
    private int size;

    @Override
    public void add(Object value) {
        array[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        isEnoughSpaceInArray();
        size++;
        for (int i = index; i < size - 1; i++) {
            array[i + 1] = array[i];
            array[i] = value;
        }
    }

    private void isEnoughSpaceInArray() {
        if(size == array.length){
            Object[] newArray = new Object[(int)(array.length * 1.5)];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    @Override
    public Object remove(int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Object removed = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removed;
    }

    @Override
    public Object get(int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Object oldObject = array[index];
        array[index] = value;
        return oldObject;
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
        for (Object x : array) {
            if(x.equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if(value.equals(array[i])){
                return i;
            }
        }
        throw new IllegalArgumentException("Array doesn't contain value!");
    }

    @Override
    public int lastIndexOf(Object value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if(array[i].equals(value)){
                index = i;
            }
        }
        if(index == -1){
            throw new IllegalArgumentException("Array doesn't contain value!");
        }
        return index;
    }
}
