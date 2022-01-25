package com.podzirey.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {

    @Test
    public void testLastIndexOf() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(1);
        arrayList.add(4);
        arrayList.add(2);
        arrayList.add(4);
        arrayList.add(2);

        assertEquals(4, arrayList.lastIndexOf(4));
        assertEquals(1, arrayList.lastIndexOf(1));
        assertEquals(5, arrayList.lastIndexOf(2));

    }

    @Test
    public void testCheckSizeAfterClear() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        assertEquals(2, arrayList.size());

        arrayList.clear();
        assertEquals(0, arrayList.size());
    }

    @Test
    public void testGetSetWorksCorrect() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        assertEquals(2, arrayList.get(1));
        arrayList.set(7, 1);
        assertEquals(7, arrayList.get(1));
        arrayList.remove(0);
        assertEquals(2, arrayList.size());
        assertEquals(7, arrayList.get(0));
        arrayList.remove(0);
        assertEquals(3, arrayList.get(0));

    }

    @Test
    public void testVerifyToString() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        assertEquals("[1,2,3]", arrayList.toString());
    }

    @Test
    public void testAddByIndexWorksCorrect() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        arrayList.add(4, 0);
        assertEquals(4, arrayList.get(0));
        arrayList.add(5, 2);
        assertEquals(5, arrayList.get(2));
        arrayList.add(6, 5);
        assertEquals(6, arrayList.get(5));
    }

    @Test
    public void testRemoveWorksCorrect() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        arrayList.remove(1);
        assertEquals(1, arrayList.get(0));
        assertEquals(3, arrayList.size());

        arrayList.remove(2);
        assertEquals(3, arrayList.get(1));
        assertEquals(2, arrayList.size());
    }

    @Test
    public void testIsEmptyWorksCorrect() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        assertFalse(arrayList.isEmpty());

        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testObjectContainsAfterAddAndRemove() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        assertTrue(arrayList.contains(3));

        arrayList.remove(2);
        assertFalse(arrayList.contains(3));
    }

    @Test
    public void testIndexOfWorksCorrect() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        assertEquals(2, arrayList.indexOf(3));

        arrayList.remove(0);
        assertEquals(1, arrayList.indexOf(3));
    }
}
