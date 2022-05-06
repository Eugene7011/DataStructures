package com.podzirey.datastructures.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {
    private final ArrayStack<String> arrayStack = new ArrayStack<>();

    @DisplayName("Test Push And Pop Work Correct")
    @Test
    public void testPushAndPopWorkCorrect() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertEquals("B", arrayStack.pop());
        assertEquals("A", arrayStack.pop());
    }

    @DisplayName("Test Size")
    @Test
    public void testSize() {
        assertEquals(0, arrayStack.size());

        arrayStack.push("A");
        assertEquals(1, arrayStack.size());

        assertEquals("A", arrayStack.pop());
        assertEquals(0, arrayStack.size());
    }

    @DisplayName("Test Push And Peek")
    @Test
    public void testPushAndPeek() {
        arrayStack.push("A");
        assertEquals("A", arrayStack.peek());

        arrayStack.push("B");
        assertEquals("B", arrayStack.peek());
    }

    @DisplayName("Test IsEmpty Return True On New Stack")
    @Test
    public void testIsEmptyReturnTrueOnNewStack() {
        assertTrue(arrayStack.isEmpty());
    }

    @DisplayName("Test IsEmpty Return False On Stack With Data")
    @Test
    public void testIsEmptyReturnFalseOnStackWithData() {
        arrayStack.push("A");
        assertFalse(arrayStack.isEmpty());
    }

    @DisplayName("Test IsEmpty Return True On Stack After Clear")
    @Test
    public void testIsEmptyReturnTrueOnStackAfterClear() {
        arrayStack.push("A");
        arrayStack.push("B");

        arrayStack.clear();
        assertTrue(arrayStack.isEmpty());
    }

    @DisplayName("Test Contains Return True")
    @Test
    public void testContainsReturnTrue() {
        arrayStack.push("A");
        arrayStack.push("B");

        assertTrue(arrayStack.contains("A"));
    }

    @DisplayName("Test Contains Return False")
    @Test
    public void testContainsReturnFalse() {
        arrayStack.push("A");
        arrayStack.push("B");
        assertFalse(arrayStack.contains("C"));
    }

    @DisplayName("Test Contains False On Empty Stack")
    @Test
    public void testContainsReturnFalseOnEmptyStack() {
        assertFalse(arrayStack.contains("C"));
    }

    @DisplayName("Test Pop Returns Null After Push Null")
    @Test
    public void testPopReturnsNullAfterPushNull() {
        arrayStack.push(null);
        assertNull(arrayStack.pop());
    }

    @DisplayName("Test Pop Throw EmptyStackException When Pop On Empty Stack")
    @Test
    public void testPopThrowIllegalStateExceptionWhenPopOnEmptyStack() {
        Assertions.assertThrows(EmptyStackException.class, () -> {
            arrayStack.pop();
        });
    }

    @DisplayName("Test Peek Throw EmptyStackException When Pop On Empty Stack")
    @Test
    public void testPeekThrowIllegalStateExceptionWhenPopOnEmptyStack() {
        Assertions.assertThrows(EmptyStackException.class, () -> {
            arrayStack.peek();
        });
    }
}
