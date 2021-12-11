package com.podzirey.datastructures.list;

import com.podzirey.datastructures.queue.ArrayQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

    @Test
    public void testAddAndSize() {
        LinkedList linkedList = new LinkedList();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        assertEquals(6, linkedList.size());

    }

    @Test
    public void testAddAndTail(){
        LinkedList linkedList = new LinkedList();

        linkedList.add(5);
        linkedList.add(3);
        linkedList.add(1);
        linkedList.add(9);
        linkedList.add(8);
        linkedList.add(7);

        assertEquals(8, (linkedList.getNode(4).value));
        assertEquals(7, (linkedList.getTail().value));
        assertEquals(5, (linkedList.getHead().value));

    }

    @Test
    public void testGetSetAndClear(){
        LinkedList linkedList = new LinkedList();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.add(8, 1);
        assertEquals(4, linkedList.size());
        assertEquals(1, linkedList.get(0));

        linkedList.set(11, 3);
        assertEquals(1, linkedList.get(0));
        assertEquals(4, linkedList.size());

        linkedList.clear();
        assertEquals(0, linkedList.size());

    }

    @Test
    public void testRemoveClearAndContains(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(6);

        assertTrue(linkedList.contains(6));
        assertFalse(linkedList.contains(7));


        linkedList.remove(0);
        assertFalse(linkedList.contains(1));
        assertEquals(6, (linkedList.getTail().value));

        linkedList.clear();
        assertEquals(0, linkedList.size());
    }

    @Test
    public void testIsEmptyReturnTrueAfterClear(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.clear();

        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testIndexOf(){
        LinkedList linkedList = new LinkedList();

        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(9);
        linkedList.add(1);

        assertEquals(4, linkedList.indexOf(1));
    }

    @Test
    public void testLastIndexOf(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(1);

        assertEquals(5, linkedList.lastIndexOf(1));
    }

    @Test
    public void testToString(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add("A");
        linkedList.add("B");

        assertEquals("[1, 2, 3, A, B]", linkedList.toString());
    }




}
