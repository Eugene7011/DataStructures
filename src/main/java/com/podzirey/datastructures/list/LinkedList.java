package com.podzirey.datastructures.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T>, Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {

        add(value, size);
    }

    private void checkIfIndexIsInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index should be in range [0] - [size - 1]");
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be in range [0] - [size]");
        }

        Node<T> newNode = new Node<>(value);
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
            Node<T> currentNode = getNode(index);
            Node<T> prevNode = currentNode.prev;

            newNode.prev = prevNode;
            newNode.next = currentNode;
            prevNode.next = newNode;
            currentNode.prev = newNode;
        }
        size++;
    }

    private Node<T> getNode(int index) {
        checkIfIndexIsInBounds(index);
        Node<T> nodeByIndex;

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
    public T remove(int index) {
        checkIfIndexIsInBounds(index);
        Node<T> removedNode = getNode(index);
        if (size == 1) {
            head = tail = null;
        } else if (index == 0) {
            head = removedNode.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = removedNode.prev;
            tail.next = null;
        } else {
            Node<T> prevNode = removedNode.prev;
            Node<T> nextNode = removedNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        size--;
        return removedNode.value;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public T set(T value, int index) {
        checkIfIndexIsInBounds(index);
        T oldObject = get(index);
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
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {

        Node<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
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

    public class MyIterator implements Iterator<T> {
        private int index;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            Node<T> current = getNode(index);
            T value = current.value;
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

    private static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private T value;

        private Node(T value) {
            this.value = value;
        }

    }
}