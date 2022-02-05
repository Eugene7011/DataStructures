package com.podzirey.datastructures.list;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

    @Test
    public void testCheckSizeAfterAdd() {
        LinkedList linkedList = new LinkedList();

        linkedList.add(1);
        linkedList.add(2);
        assertEquals(2, linkedList.size());

        linkedList.add(3);
        linkedList.add(1);
        assertEquals(4, linkedList.size());

        linkedList.add(2);
        linkedList.add(3);

        assertEquals(6, linkedList.size());

    }

    @Test
    public void testAddByIndexWorksCorrect() {
        LinkedList linkedList = new LinkedList();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.add(4, 0);
        assertEquals(4, linkedList.get(0));
        linkedList.add(5, 2);
        assertEquals(5, linkedList.get(2));
        linkedList.add(6, 5);
        assertEquals(6, linkedList.get(5));

        assertEquals(6, linkedList.size());

    }

    @Test
    public void testCheckTailAndHeadAfterAdd() {
        LinkedList linkedList = new LinkedList();

        linkedList.add(5);
        linkedList.add(3);
        linkedList.add(1);
        linkedList.add(9);
        linkedList.add(8);
        linkedList.add(7);

        assertEquals(8, linkedList.get(4));
        assertEquals(7, linkedList.get(linkedList.size() - 1));
        assertEquals(5, linkedList.get(0));

    }

    @Test
    public void testGetSetAndClear() {
        LinkedList linkedList = new LinkedList();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.add(8, 3);
        assertEquals(4, linkedList.size());
        assertEquals(1, linkedList.get(0));

        assertEquals(8, linkedList.set(11, 3));
        assertEquals(1, linkedList.get(0));
        assertEquals(4, linkedList.size());
        assertEquals(11, linkedList.get(3));

        linkedList.clear();
        assertEquals(0, linkedList.size());
    }

    @Test
    public void testCheckContainsAfterAddRemoveAndClear() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        assertEquals(1, linkedList.get(0));
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(6);

        assertEquals(5, linkedList.size());

        assertTrue(linkedList.contains(6));
        assertFalse(linkedList.contains(7));

        assertEquals(1, linkedList.remove(0));
        assertFalse(linkedList.contains(1));
        assertEquals(6, linkedList.get(linkedList.size() - 1));
        assertEquals(2, linkedList.get(0));

        linkedList.clear();
        assertEquals(0, linkedList.size());

        linkedList.add(1);
        linkedList.remove(0);
        assertTrue(linkedList.isEmpty());

    }

    @Test
    public void testIsEmptyReturnsTrueAfterClear() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.clear();

        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testIndexOfWorksCorrect() {
        LinkedList linkedList = new LinkedList();

        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(3);
        linkedList.add(1);

        assertEquals(4, linkedList.indexOf(1));
        assertEquals(1, linkedList.indexOf(3));
        assertEquals(0, linkedList.indexOf(2));

    }

    @Test
    public void testCheckHeadAfterAdd() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(2);

        assertEquals(1, linkedList.get(0));
        assertEquals(2, linkedList.get(3));

    }

    @Test
    public void testLastIndexOfWorksCorrect() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(1);

        assertEquals(5, linkedList.lastIndexOf(1));
        assertEquals(3, linkedList.lastIndexOf(2));
        assertEquals(4, linkedList.lastIndexOf(3));
    }

    @Test
    public void testVerifyToString() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add("A");
        linkedList.add("B");

        assertEquals("[1,2,3,A,B]", linkedList.toString());
    }

    @Test
    public void testIteratorWorksCorrect() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        Iterator iterator = linkedList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
