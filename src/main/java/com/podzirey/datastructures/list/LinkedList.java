package com.podzirey.datastructures.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> extends AbstractList<T> {

    private Node<T> head;
    private Node<T> tail;

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            if (size == 0) {
                throw new IndexOutOfBoundsException("Index is " + index + " but should be [0] because list is empty now");
            }
            throw new IndexOutOfBoundsException("Index is " + index + " but should be in range [0," + size + "]");
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
        Node<T> nodeByIndex;

        if (index < size / 2) {
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
        checkIfIndexIsInBounds(index);
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
    public int indexOf(Object value) {
        Node<T> current = head;
        if (Objects.isNull(value)) {
            for (int i = 0; i < size; i++) {
                if (current.value == null) {
                    return i;
                }
                current = current.next;
            }
        }
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
        if (Objects.isNull(value)) {
            for (int i = 0; i < size; i++) {
                if (current.value == null) {
                    return i;
                }
                current = current.next;
            }
        }
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(current.value, value)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    public class MyIterator implements Iterator<T> {
        private int index;
        Node<T> current = head;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element is the List");
            }
            T value = current.value;
            current = current.next;
            canRemove = true;
            return value;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Already removed");
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