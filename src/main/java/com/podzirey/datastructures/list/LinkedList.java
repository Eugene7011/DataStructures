package com.podzirey.datastructures.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList implements List, Iterable {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(Object value) {
        add(value, size);
    }

    public void checkIfIndexIsInBounds(int index){
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index should be in range [0] - [size - 1]");
        }
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0] - [size]");
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
        checkIfIndexIsInBounds(index);
        Node nodeByIndex;

        if (index < (size / 2)) {
            nodeByIndex = head;
            for (int i = 0; i < index; i++) {
                nodeByIndex = nodeByIndex.next;
            }
        } else {
            nodeByIndex = tail;
            for (int i = size - 1; i > index; i--) {
                nodeByIndex = nodeByIndex.prev;
            }
        }
        return nodeByIndex;
    }

    @Override
    public Object remove(int index) {
        checkIfIndexIsInBounds(index);
        Node removedNode = getNode(index);
        if (size == 1) {
            head = tail = null;
        } else if (index == 0) {
            head = removedNode.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = removedNode.prev;
            tail.next = null;
        } else {
            Node prevNode = removedNode.prev;
            Node nextNode = removedNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
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
        checkIfIndexIsInBounds(index);
        Object oldObject = get(index);
        getNode(index).value = value;
        return oldObject;
    }

    @Override
    public void clear() {
        head = tail = null;
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
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {

        Node current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        for (Object value : this) {
            stringJoiner.add(String.valueOf(value));
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    public class MyIterator implements Iterator {
        private int index;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Object next() {
            Node current = getNode(index);
            Object value = current.value;
            index++;
            canRemove = true;
            return value;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalArgumentException("Already removed");
            }
            LinkedList.this.remove(index - 1);
            index--;
            canRemove = false;
        }
    }

    private static class Node {
        private Node next;
        private Node prev;
        private Object value;

        private Node(Object value) {
            this.value = value;
        }

    }
}