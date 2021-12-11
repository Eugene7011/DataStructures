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

        assertEquals(3, linkedList.size());

    }

    @Test
    public void testAddAndTail(){
        LinkedList linkedList = new LinkedList();

        linkedList.add(5);
        linkedList.add(3);
        linkedList.add(1);

        assertEquals(1, (linkedList.getNode(2).value));
        assertEquals(1, (linkedList.getTail().value));
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
        assertEquals(8, linkedList.get(1));

        linkedList.set(11, 3);
        assertEquals(11, linkedList.get(3));

        linkedList.clear();
        assertEquals(0, linkedList.size());

    }

    @Test
    public void testRemoveClearAndContains(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        assertTrue(linkedList.contains(2));
        assertFalse(linkedList.contains(7));


        linkedList.remove(2);
        assertFalse(linkedList.contains(3));
        assertEquals(2, (linkedList.getTail().value));

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
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        assertEquals(2, linkedList.indexOf(3));
    }

    @Test
    public void testLastIndexOf(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(3);

        assertEquals(4, linkedList.lastIndexOf(3));
    }

    public void testToString(){
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(3);

        assertEquals("[1, 2, 3, 2, 3]", linkedList.toString());
    }


}
