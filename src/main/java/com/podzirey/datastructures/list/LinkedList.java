package com.podzirey.datastructures.list;

import java.util.StringJoiner;

public class LinkedList implements List{

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(Object value) {
        Node newNode = new Node(value);
        if(size==0){
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(Object value, int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node newNode = new Node(value);
        if(size==0){
            head = tail = newNode;
        } else if (index == size -1){
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else if(index == 0){
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if(index != 0 & index < size - 1){
            Node currentNode = getNode(index);
            Node prevNode = currentNode.prev;

            newNode.prev = prevNode;
            newNode.next = currentNode;
            prevNode.next = newNode;
            currentNode.prev = newNode;

        }
        size++;
    }

    @Override
    public Object remove(int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node removedNode = getNode(index);
        if(index == 0){
            head = removedNode.next;
            head.prev = null;
        } else if(index == size - 1){
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

    public Node getNode(int index){
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node nodeByIndex = new Node();
        if(index == 0){
            nodeByIndex = head;
        } else if(index == size - 1) {
            nodeByIndex = tail;
        }

        if(0 < index & index < (size/2)) {
            nodeByIndex = head.next;
            for (int i = 1; i < index; i++) {
                nodeByIndex = nodeByIndex.next;
            }
        }
        if((size/2) <= index & index < size - 1) {
            nodeByIndex = tail.prev;
            for (int i = 1; i < (size - 1 - index); i++) {
                nodeByIndex = nodeByIndex.prev;
            }
        }
        return nodeByIndex;
    }

    public Node getTail(){
        Node tail = getNode(size - 1);
        return tail;
    }

    public Node getHead(){
        Node head = getNode(0);
        return head;
    }

    @Override
    public Object get(int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node nodeByIndex = new Node();
        if(index == 0){
            nodeByIndex = head;
        } else if(index == size - 1) {
            nodeByIndex = tail;
        }

        if(0 < index && index < (size/2)) {
            nodeByIndex = head.next;
            for (int i = 1; i < index; i++) {
                nodeByIndex = nodeByIndex.next;
            }
        }
        if((size/2) <= index && index < size - 1) {
            nodeByIndex = tail.prev;
            for (int i = 1; i < (size - 1 - index); i++) {
                nodeByIndex = nodeByIndex.prev;
            }
        }
        return nodeByIndex.value;
    }

    @Override
    public Object set(Object value, int index) {
        if(index < 0 || index > size - 1){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Object oldObject = get(index);
        getNode(index).value = value;
        return oldObject;
    }

    @Override
    public void clear() {
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
        for (int i = 0; i < size; i++) {
            if(value.equals(get(i))){
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(Object value) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if(value.equals(get(i))){
                count++;
            }
            if (count > 1){
                throw new IllegalArgumentException("List contains several objects with entered value!");
            }
            if (i == size -1){
                return i;
            }
        }
        throw new IllegalArgumentException("List doesn't contain value!");
    }

    @Override
    public int lastIndexOf(Object value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if(value.equals(get(i))){
                index = i;
            }
        }
        if(index == -1){
            throw new IllegalArgumentException("List doesn't contain value!");
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
