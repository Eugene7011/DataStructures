package com.podzirey.datastructures.stack;

public interface Stack {
    void push(Object value);

    Object pop();

    Object peek();

    boolean contains(Object value);

    int size();

    boolean isEmpty();

    void clear();
}
