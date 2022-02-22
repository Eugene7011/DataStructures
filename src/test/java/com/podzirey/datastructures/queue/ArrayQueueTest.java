package com.podzirey.datastructures.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayQueueTest {

    @Test
    public void testEnqueueAndDequeueWorkCorrectlyAndChangeSize(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");

        assertEquals(2, arrayQueue.size());
        assertEquals("Q", arrayQueue.dequeue());
        assertEquals("W", arrayQueue.dequeue());
        assertEquals(0, arrayQueue.size());
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testEnqueueAndDequeueAndPeek(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");
        arrayQueue.enqueue("E");

        assertEquals("Q", arrayQueue.peek());
        assertEquals("Q", arrayQueue.dequeue());

        assertEquals("W", arrayQueue.peek());
        assertEquals("W", arrayQueue.dequeue());

        assertEquals(1, arrayQueue.size());

        assertFalse(arrayQueue.isEmpty());

        assertEquals("E", arrayQueue.peek());
        assertEquals("E", arrayQueue.dequeue());

        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testIsEmptyReturnTrueOnQueueAfterClear(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");

        arrayQueue.clear();
        assertTrue(arrayQueue.isEmpty());
    }

    @Test
    public void testContainsReturnTrue(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("Q");

        assertTrue(arrayQueue.contains("Q"));
    }

    @Test
    public void testContainsReturnFalse(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("Q");

        assertFalse(arrayQueue.contains("J"));
    }

    @Test
    public void testContainsReturnFalseOnEmptyQueue(){
        ArrayQueue arrayQueue = new ArrayQueue();
        assertFalse(arrayQueue.contains("SHO"));
    }

    @Test
    public void testToStringWorksCorrectlyOnFullQueue(){
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enqueue("Q");
        arrayQueue.enqueue("W");
        arrayQueue.enqueue("E");

        assertEquals("[Q,W,E,null,null,null,null]", arrayQueue.toString());
    }

    @Test
    public void testThrowIllegalStateExceptionWhenDequeueOnEmptyQueue(){
        ArrayQueue arrayQueue = new ArrayQueue();
        Assertions.assertThrows(IllegalStateException.class, () -> {
            arrayQueue.dequeue();
        });
    }

}
