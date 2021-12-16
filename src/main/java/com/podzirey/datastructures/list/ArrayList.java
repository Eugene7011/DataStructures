package com.podzirey.datastructures.list;

import javax.management.ValueExp;
import java.util.StringJoiner;

public class ArrayList implements  List{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] array;
    private int size;
    private final int size2 = 10;

    public ArrayList(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int capacity){
        this.array = new Object[capacity];
    }

    public int getSize2() {
        return size2;
    }

    @Override
    public void add(Object value) {
        isEnoughSpaceInArray();
        array[size] = value;
        size++;
    }

    @Override
    public void add(Object value, int index) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        isEnoughSpaceInArray();

        System.arraycopy(array, index, array, index + 1, size - index);
        size++;
        array[index] = value;
    }

    private void isEnoughSpaceInArray() {
        if(size == array.length){
            double newSize = size * 1.5;
            Object[] newArray = new Object[(int)(newSize)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Override
    public Object remove(int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Object removed = array[index];

        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size -1] = null;
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
        if(value == null){
            throw new IllegalArgumentException("Object value can't be null!");
        }
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

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(get(i).toString());
        }
        return stringJoiner.toString();
    }

}
