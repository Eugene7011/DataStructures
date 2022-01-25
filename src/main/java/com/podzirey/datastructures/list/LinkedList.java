package com.podzirey.datastructures.list;

import java.util.StringJoiner;

public class LinkedList implements List {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (value == null) {
            throw new IllegalArgumentException("Invalid object value");
        }

        Node newNode = new Node(value);
        if (size == 0) {
            head = tail = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node currentNode = getNode(index);
            Node prevNode = currentNode.prev;

            newNode.prev = prevNode;
            newNode.next = currentNode;
            prevNode.next = newNode;
            currentNode.prev = newNode;
        }
        size++;
    }

    private Node getNode(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node nodeByIndex = new Node();

        if (index == 0) {
            nodeByIndex = head;
        } else if (index == size - 1) {
            nodeByIndex = tail;
        } else if (index < (size / 2)) {
            nodeByIndex = head.next;
            for (int i = 1; i < index; i++) {
                nodeByIndex = nodeByIndex.next;
            }
        } else if ((size / 2) <= index & index < size - 1) {
            nodeByIndex = tail.prev;
            for (int i = 1; i < (size - 1 - index); i++) {
                nodeByIndex = nodeByIndex.prev;
            }
        }
        return nodeByIndex;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node removedNode = getNode(index);
        if(size == 1){
            head = tail = null;
        } else{
            if(index == 0){
                head = removedNode.next;
                head.prev = null;
            } else if(index == size - 1){
                tail = removedNode.prev;
                tail.next = null;
            } else if(index < size - 1){
                Node prevNode = removedNode.prev;
                Node nextNode = removedNode.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
        }
        size--;
        return removedNode.value;
    }

    @Override
    public Object get(int index) {
        return getNode(index).value;
    }

    @Override
    public Object set(Object value, int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (value == null) {
            throw new IllegalArgumentException("Invalid object value");
        }
        Object oldObject = get(index);
        getNode(index).value = value;
        return oldObject;
    }

    @Override
    public void clear() {
        if(isEmpty()){
            throw new IllegalArgumentException("List is already Empty!");
        }
        for (int i = 0; i < size; i++) {
            getNode(i).value = null;
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
            throw new IllegalArgumentException("Invalid object value");
        }
        if(isEmpty()){
            throw new IllegalArgumentException("List is Empty!");
        }
        for (int i = 0; i < size; i++) {
            if (get(i).equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid object value");
        }
        if(isEmpty()){
            throw new IllegalArgumentException("List is Empty!");
        }
        if (contains(value)){
            for (int i = 0; i < size; i++) {
                if (get(i).equals(value)){
                    return i;
                }
            }
        }
       return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid object value");
        }
        if(isEmpty()){
            throw new IllegalArgumentException("List is Empty!");
        }
        int lastIndexOf = -1;
        if (contains(value)){
            for (int i = 0; i < size; i++) {
                if (get(i).equals(value)){
                    lastIndexOf = i;
                }
            }
        }
        return lastIndexOf;
    }

    public Node getTail() {
        if(isEmpty()){
            throw new IllegalArgumentException("List is Empty!");
        }
        return getNode(size - 1);
    }

    public Node getHead() {
        if(isEmpty()){
            throw new IllegalArgumentException("List is Empty!");
        }
        return getNode(0);
    }

    public String toString() {
        if(isEmpty()){
            throw new IllegalArgumentException("List is Empty!");
        }
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(get(i).toString());
        }
        return stringJoiner.toString();
    }
}
