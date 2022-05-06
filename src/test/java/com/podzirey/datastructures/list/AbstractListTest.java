package com.podzirey.datastructures.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {
    private List<Integer> list;

    @BeforeEach
    public void before() {
        list = getList();
    }

    protected abstract List<Integer> getList();

    @DisplayName("Test Last IndexOf")
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

    @DisplayName("Test Check Size After Clear")
    @Test
    public void testCheckSizeAfterClear() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());

        list.clear();
        assertEquals(0, list.size());
    }

    @DisplayName("Test Add Works Correct")
    @Test
    public void testAddWorksCorrect() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
    }

    @DisplayName("Test Set Works Correct")
    @Test
    public void testSetWorksCorrect() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.set(7, 1);

        assertEquals(3, list.size());
        assertEquals(7, list.get(1));
    }

    @DisplayName("Test Verify Null")
    @Test
    public void testGetReturnsNullAfterAddingNull() {
        list.add(1);
        list.add(null);
        list.add(3);

        assertNull(list.get(1));
    }

    @DisplayName("Test Verify ToString")
    @Test
    public void testVerifyToString() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(null);

        assertEquals("[1,2,3,null]", list.toString());
    }

    @DisplayName("Test Add By Index Works Correct")
    @Test
    public void testAddByIndexWorksCorrect() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4, 0);
        list.add(5, 2);
        list.add(6, 5);

        assertEquals("[4,1,5,2,3,6]", list.toString());
    }

    @DisplayName("Test RemoveWorksCorrect")
    @Test
    public void testRemoveWorksCorrect() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(2, list.remove(1));

        assertEquals(4, list.remove(2));
        assertEquals(3, list.get(1));
        assertEquals(2, list.size());
    }

    @DisplayName("Test Is Empty Works Correct")
    @Test
    public void testIsEmptyWorksCorrect() {
        assertTrue(list.isEmpty());

        list.add(1);
        list.add(2);
        list.add(3);

        assertFalse(list.isEmpty());
    }

    @DisplayName("Test Clear Works Correct")
    @Test
    public void testClearWorksCorrect() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.clear();
        assertTrue(list.isEmpty());
    }

    @DisplayName("Test Contains After Add And Remove")
    @Test
    public void testContainsAfterAddAndRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(3));

        list.remove(2);
        assertFalse(list.contains(3));
    }

    @DisplayName("Test Index Of Works Correct")
    @Test
    public void testIndexOfWorksCorrect() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(2, list.indexOf(3));
        assertEquals(1, list.indexOf(2));
        assertEquals(0, list.indexOf(1));
    }

    @DisplayName("Test Index Of Works Correct With Null")
    @Test
    public void testIndexOfWorksCorrectWithNull() {
        list.add(1);
        list.add(2);
        list.add(null);

        assertEquals(2, list.indexOf(null));
    }

    @DisplayName("Test Iterator Has Next And Next Works Correct")
    @Test
    public void testIteratorHasNextAndNextWorksCorrect() {
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @DisplayName("Test Iterator Remove Works Correct")
    @Test
    public void testIteratorRemoveWorksCorrect() {
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();

        iterator.next();
        iterator.remove();
        assertEquals(2, iterator.next());
    }

    @DisplayName("Test Iterator Remove Throw IllegalStateException")
    @Test
    public void testIteratorRemoveThrowIllegalStateException() {
        list.add(1);
        Iterator<Integer> iterator = list.iterator();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });

    }

    @DisplayName("Test Get Throw Index Out Of Bounds Exception On Empty List")
    @Test
    public void testGetThrowIndexOutOfBoundsExceptionOnEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
    }

    @DisplayName("Test Set Throw Index Out Of Bounds Exception On Empty List")
    @Test
    public void testSetThrowIndexOutOfBoundsExceptionOnEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(0, 0);
        });
    }

    @DisplayName("Test Remove Throw Index Out Of Bounds Exception On Empty List")
    @Test
    public void testRemoveThrowIndexOutOfBoundsExceptionOnEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.clear();
            list.remove(0);
        });
    }

    @DisplayName("Test Get Throw Index Out Of Bounds Exception When Index Is Negative")
    @Test
    public void testGetThrowIndexOutOfBoundsExceptionWhenIndexIsNegative() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(1);
            list.get(-3);
        });
    }

    @DisplayName("Test Remove Throw Index Out Of Bounds Exception When Index Is Bigger Than Size")
    @Test
    public void testRemoveThrowIndexOutOfBoundsExceptionWhenIndexIsBiggerThanSize() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(2);
            list.add(2);
            list.add(2);
            list.remove(3);
        });
    }

    @DisplayName("Test Add Throw Index Out Of Bounds Exception When Index Is Out Of Size")
    @Test
    public void testAddThrowIndexOutOfBoundsExceptionWhenIndexIsOutOfSize() {
        IndexOutOfBoundsException indexOutOfBoundsException =
                assertThrows(IndexOutOfBoundsException.class, () -> {
                    list.add(2, 2);
                });
        assertEquals("Index is 2 but should be [0] because list is empty now", indexOutOfBoundsException.getMessage());
    }

    @DisplayName("Test Contains Throw Index Out Of Bounds Exception When Index Is Out Of Size")
    @Test
    public void testContainsThrowIndexOutOfBoundsExceptionWhenIndexIsOutOfSize() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(1, -2);
        });
    }

    @DisplayName("Test Index Of Returns Minus One On Not Existing Element")
    @Test
    public void testIndexOfReturnsMinusOneOnNotExistingElement() {
        list.add(1);
        list.add(null);
        list.add(3);

        assertEquals(-1, list.indexOf(4));
    }
}
