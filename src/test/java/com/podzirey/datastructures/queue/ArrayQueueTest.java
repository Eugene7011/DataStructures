package com.podzirey.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayQueueTest {
    private final ArrayQueue arrayQueue = new ArrayQueue();

    @DisplayName("Test Enqueue And Dequeue Works Correct And Changes Size")
    @Test
    public void testEnqueueAndDequeueWorkCorrectlyAndChangeSize() {
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");

        assertEquals("Q", arrayQueue.dequeue());
        assertEquals("W", arrayQueue.dequeue());
    }

    @DisplayName("Test Enqueue And DequeueWorkCorrectlyAndChangeSize")
    @Test
    public void testSizeWorksCorrect() {
        assertEquals(0, arrayQueue.size());

        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");

        assertEquals(2, arrayQueue.size());
    }

    @DisplayName("Test Peek")
    @Test
    public void testPeek() {
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");
        arrayQueue.enqueue("E");

        assertEquals("Q", arrayQueue.peek());

        assertEquals("Q", arrayQueue.dequeue());
        assertEquals("W", arrayQueue.peek());

        assertEquals("W", arrayQueue.dequeue());
        assertEquals("E", arrayQueue.peek());
    }

    @DisplayName("Test IsEmpty")
    @Test
    public void testIsEmpty() {
        assertTrue(arrayQueue.isEmpty());

        arrayQueue.enqueue("Q");
        arrayQueue.dequeue();

        assertTrue(arrayQueue.isEmpty());
    }

    @DisplayName("Test IsEmpty Return True On Queue After Clear")
    @Test
    public void testIsEmptyReturnTrueOnQueueAfterClear() {
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");

        arrayQueue.clear();
        assertTrue(arrayQueue.isEmpty());
    }

    @DisplayName("Test Contains Return True On Existing Element")
    @Test
    public void testContainsReturnTrueOnExistingElement() {
        arrayQueue.enqueue("Q");
        assertTrue(arrayQueue.contains("Q"));
    }

    @DisplayName("Test Contains Return False On Not Existing Element")
    @Test
    public void testContainsReturnFalseOnNotExistingElement() {
        arrayQueue.enqueue("Q");
        assertFalse(arrayQueue.contains("J"));
    }

    @DisplayName("Test Contains Return False On Empty Queue")
    @Test
    public void testContainsReturnFalseOnEmptyQueue() {
        assertFalse(arrayQueue.contains("SHO"));
    }

    @DisplayName("Test ToString Works Correct On Not Empty Queue")
    @Test
    public void testToStringWorksCorrectlyOnNotEmptyQueue() {
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");
        arrayQueue.enqueue("E");

        assertEquals("[Q,W,E,null,null,null,null]", arrayQueue.toString());
    }

    @DisplayName("Test Throw IllegalStateException When Dequeue On Empty Queue")
    @Test
    public void testThrowIllegalStateExceptionWhenDequeueOnEmptyQueue() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayQueue.dequeue();
        });
    }

}
