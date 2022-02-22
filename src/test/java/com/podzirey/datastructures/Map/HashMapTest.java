package com.podzirey.datastructures.Map;

import com.podzirey.datastructures.map.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
    private HashMap hashMap = new HashMap();

    @Test
    public void testPut() {

        assertNull(hashMap.put("key1", "value1"));
        assertEquals(1, hashMap.size());

        assertNull(hashMap.put("key2", "value2"));
        assertEquals(2, hashMap.size());

        assertEquals("value1", hashMap.put("key1", "value3"));
        assertEquals(2, hashMap.size());
    }

    @Test
    public void testPutAndGet() {
        assertNull(hashMap.put("key1", "value1"));
        assertEquals("value1", hashMap.get("key1"));

        assertNull(hashMap.get("key2"));
    }

    @Test
    public void testPutAndContains() {
        assertNull(hashMap.put("key1", "value1"));
        assertEquals("value1", hashMap.put("key1", "value2"));

        assertNull(hashMap.put("key2", "value3"));
        assertNull(hashMap.put("key3", "value4"));

        assertTrue(hashMap.containsKey("key1"));
        assertTrue(hashMap.containsKey("key2"));
        assertTrue(hashMap.containsKey("key3"));
        assertFalse(hashMap.containsKey("key4"));

        assertFalse(hashMap.containsKey("null"));
        assertNull(hashMap.get("null"));
    }

    @Test
    public void givenEmptyHashMapWhenGetByNullKeyThenNullShouldBeReturned() {
        assertNull(hashMap.get("null"));
    }

    @Test
    public void givenEmptyHashMapWhenGetByNotNullKeyThenNullShouldBeReturned() {
        assertNull(hashMap.get("key"));
    }

    @Test
    public void givenNotEmptyHashMapWhenGetByNullKeyThenNullShouldBeReturned() {
        assertNull(hashMap.put("key1", "value1"));
        assertNull(hashMap.put("key2", "value2"));
        assertNull(hashMap.put("key3", "value3"));
        assertNull(hashMap.put("key4", "value4"));
        assertNull(hashMap.put("key5", "value5"));
        assertNull(hashMap.put("key6", "value6"));
        assertEquals(6, hashMap.size());
        assertNull(hashMap.get("null"));
    }

    @Test
    public void givenNotEmptyHashMapWhenGetByExistingNullKeyThenValueShouldBeReturned() {
        assertNull(hashMap.put("null", "value1"));
        assertEquals("value1", hashMap.get("null"));
    }

    @Test
    public void givenEmptyHashMapWhenContainsByNullThenFalseShouldBeReturned() {
        assertFalse(hashMap.containsKey("null"));
    }

    @Test
    public void givenEmptyHashMapWhenPutNullContainsByNullShouldReturnTrue() {
        assertNull(hashMap.put("null", "null"));
        assertTrue(hashMap.containsKey("null"));
    }

    @Test
    public void givenEmptyHashMapWhenIsEmptyThenTrueShouldBeReturned() {
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void givenEmptyHashMapWhenPutOnceThenIsEmptyShouldReturnFalse() {
        assertNull(hashMap.put("key1", "value1"));
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void givenEmptyHashMapWhenPutNullThenIsEmptyShouldReturnFalse() {
        assertNull(hashMap.put("null", "null"));
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void givenEmptyHashMapWhenRemoveByNullThenNullShouldBeReturned() {
        assertNull(hashMap.remove("null"));
    }

    @Test
    public void givenEmptyHashMapWhenPutValueAndRemoveByThisKeyThenValueShouldBeReturnedAndIsEmptyTrue() {
        assertNull(hashMap.put("key", "value"));
        assertEquals("value", hashMap.remove("key"));
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void givenEmptyHashMapWhenPutNullKeyAndValueAndRemoveByNullThenValueShouldBeReturnedAndIsEmptyTrue() {
        assertNull(hashMap.put("null", "value"));
        assertEquals("value", hashMap.remove("null"));
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testKeyGeneralCase() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        assertEquals(3, hashMap.key().size());
        assertTrue(hashMap.key().contains("key1"));
        assertTrue(hashMap.key().contains("key2"));
        assertTrue(hashMap.key().contains("key3"));
    }

    @Test
    public void givenEmptyHashMapWhenPutNullKeyThenKeyListShouldContainNull() {
        hashMap.put("null", "null");

        assertTrue(hashMap.key().contains("null"));
    }

    @Test
    public void testValuesGeneralCase() {
        hashMap.put("key1", "value1");
        assertEquals("[key1]", hashMap.key().toString());
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        assertEquals(3, hashMap.key().size());
        assertTrue(hashMap.values().contains("value1"));
        assertTrue(hashMap.values().contains("value2"));
        assertTrue(hashMap.values().contains("value3"));
    }

    @Test
    public void givenEmptyHashMapWhenPutNullKeyThenValuesListShouldContainNull() {
        hashMap.put("null", "null");

        assertTrue(hashMap.values().contains("null"));
    }

    @Test
    public void testPutIfAbsent() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        assertEquals("value3", hashMap.putIfAbsent("key3", "newValue"));
        assertEquals("value3", hashMap.get("key3"));

        assertNull(hashMap.putIfAbsent("key4", "newValue"));
        assertEquals("newValue", hashMap.get("key4"));
    }

    @Test
    public void testPutAll() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        HashMap extraHashMap = new HashMap();
        extraHashMap.put("KEY1", "VALUE1");
        extraHashMap.put("KEY2", "VALUE3");
        extraHashMap.put("KEY3", "VALUE3");
        hashMap.putAll(extraHashMap);

        assertEquals(6, hashMap.size());
        assertEquals("VALUE1", hashMap.get("KEY1"));
        assertTrue(hashMap.containsKey("KEY2"));
        assertEquals("VALUE3", hashMap.remove("KEY3"));
    }
}
