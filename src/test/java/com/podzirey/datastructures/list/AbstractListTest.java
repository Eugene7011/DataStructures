package com.podzirey.datastructures.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {
    private List list;

    @BeforeEach
    public void before() {
        list = getList();
    }

    protected abstract List getList();

    @Test
    public void testLastIndexOf() {

        list.add(1);
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(4);
        list.add(2);

        assertEquals(4, list.lastIndexOf(4));
        assertEquals(1, list.lastIndexOf(1));
        assertEquals(5, list.lastIndexOf(2));

    }

    @Test
    public void testCheckSizeAfterClear() {

        list.add(1);
        list.add(2);
        assertEquals(2, list.size());

        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testAddSetRemoveWorksCorrect() {

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(2, list.get(1));
        list.set(7, 1);
        assertEquals(7, list.get(1));
        list.remove(0);
        assertEquals(2, list.size());
        assertEquals(7, list.get(0));
        list.remove(0);


    }

    @Test
    public void testVerifyNull() {

        list.add(1);
        list.add(null);
        list.add(3);
        assertNull(list.get(1));
    }

    @Test
    public void testVerifyToString() {

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null);
        assertEquals("[1,2,3,null]", list.toString());
    }

    @Test
    public void testAddByIndexWorksCorrect() {

        list.add(1);
        list.add(2);
        list.add(3);

        list.add(4, 0);
        assertEquals(4, list.get(0));
        list.add(5, 2);
        assertEquals(5, list.get(2));
        list.add(6, 5);
        assertEquals(6, list.get(5));
    }

    @Test
    public void testRemoveWorksCorrect() {

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(2, list.remove(1));
        assertEquals(1, list.get(0));
        assertEquals(3, list.size());

        assertEquals(4, list.remove(2));
        assertEquals(3, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testIsEmptyWorksCorrect() {

        list.add(1);
        list.add(2);
        list.add(3);

        assertFalse(list.isEmpty());

        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testObjectContainsAfterAddAndRemove() {

        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(3));

        list.remove(2);
        assertFalse(list.contains(3));
    }

    @Test
    public void testIndexOfWorksCorrect() {

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(2, list.indexOf(3));

        list.remove(0);
        assertEquals(1, list.indexOf(3));
    }

    @Test
    public void testIteratorWorksCorrect() {

        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testGetThrowIndexOutOfBoundsExceptionOnEmptyList() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.clear();
            list.get(0);
        });
    }

    @Test
    public void testSetThrowIndexOutOfBoundsExceptionOnEmptyList() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.clear();
            list.set(0, 0);
        });
    }

    @Test
    public void testRemoveThrowIndexOutOfBoundsExceptionOnEmptyList() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.clear();
            list.remove(0);
        });
    }

    @Test
    public void testGetThrowIndexOutOfBoundsExceptionWhenIndexIsNegative() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(1);
            list.add(2);
            list.get(-3);
        });
    }

    @Test
    public void testRemoveThrowIndexOutOfBoundsExceptionWhenIndexIsBiggerThanSize() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(2);
            list.add(2);
            list.add(2);
            list.remove(4);
        });
    }

    @Test
    public void testAddThrowIndexOutOfBoundsExceptionWhenIndexIsOutOfSize() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(2, 2);
        });
    }

    @Test
    public void testContainsThrowIndexOutOfBoundsExceptionWhenIndexIsOutOfSize() {

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(1, -2);
        });
    }
}
