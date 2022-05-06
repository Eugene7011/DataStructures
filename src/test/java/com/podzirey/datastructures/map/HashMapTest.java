package com.podzirey.datastructures.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
    private final Map<String, String> hashMap = new HashMap<>();

    @DisplayName("Test Put Returns Null On Empty Map")
    @Test
    public void testPutReturnsNullOnEmptyMap() {
        //prepare
        assertTrue(hashMap.isEmpty());

        //then
        assertNull(hashMap.put("key1", "value1"));
    }

    @DisplayName("Test When Put Entry On Empty Map Then Its Value Should Be Returned When Get By This Key")
    @Test
    public void testWhenPutEntryOnEmptyMapThenItsValueShouldBeReturnedWhenGetByThisKey() {
        //prepare
        assertTrue(hashMap.isEmpty());

        //when
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        //then
        assertEquals("value1", hashMap.get("key1"));
        assertEquals("value2", hashMap.get("key2"));
        assertEquals("value3", hashMap.get("key3"));
    }

    @DisplayName("Test Put On Existing Key And Get")
    @Test
    public void testPutOnExistingKeyAndGet() {
        //prepare
        assertTrue(hashMap.isEmpty());
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        //when
        hashMap.put("key2", "newValue");

        //then
        assertEquals("newValue", hashMap.get("key2"));
    }

    @DisplayName("Test contains when on entries which were put")
    @Test
    public void testWhenPutEntriesThenContainsByEntriesKeyShouldReturnEntriesValue() {
        //when
        assertNull(hashMap.put("key1", "value1"));
        assertNull(hashMap.put("key2", "value2"));
        assertNull(hashMap.put("key3", "value3"));

        //then
        assertTrue(hashMap.containsKey("key1"));
        assertTrue(hashMap.containsKey("key2"));
        assertTrue(hashMap.containsKey("key3"));
    }

    @DisplayName("Test contains returns null after null value was put")
    @Test
    public void testWhenPutNullValueThenContainsEntryKeyShouldReturnNullValue() {
        //when
        assertNull(hashMap.put(null, "value"));

        //then
        assertTrue(hashMap.containsKey(null));
    }

    @Test
    public void givenEmptyHashMapWhenGetByNullKeyThenNullShouldBeReturned() {
        assertNull(hashMap.get(null));
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
        assertNull(hashMap.get(null));

        assertEquals("value6", hashMap.get("key6"));
        assertEquals("value5", hashMap.get("key5"));
        assertEquals("value4", hashMap.get("key4"));
        assertEquals("value3", hashMap.get("key3"));
        assertEquals("value2", hashMap.get("key2"));
        assertEquals("value1", hashMap.get("key1"));
    }

    @Test
    public void givenNotEmptyHashMapWhenGetByExistingNullKeyThenValueShouldBeReturned() {
        assertNull(hashMap.put(null, "value1"));
        assertEquals("value1", hashMap.get(null));
    }

    @Test
    public void givenEmptyHashMapWhenContainsByNullThenFalseShouldBeReturned() {
        assertFalse(hashMap.containsKey(null));
    }

    @Test
    public void givenEmptyHashMapWhenPutNullContainsByNullShouldReturnTrue() {
        assertNull(hashMap.put(null, null));
        assertTrue(hashMap.containsKey(null));
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
        assertNull(hashMap.put(null, null));
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void givenEmptyHashMapWhenRemoveByNullThenNullShouldBeReturned() {
        assertNull(hashMap.remove(null));
    }

    @Test
    public void givenEmptyHashMapWhenPutValueAndRemoveByThisKeyThenValueShouldBeReturnedAndIsEmptyTrue() {
        assertNull(hashMap.put("key", "value"));
        assertEquals("value", hashMap.remove("key"));
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void givenEmptyHashMapWhenPutNullKeyAndValueAndRemoveByNullThenValueShouldBeReturnedAndIsEmptyTrue() {
        assertNull(hashMap.put(null, "value"));
        assertEquals("value", hashMap.remove(null));
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testKeyGeneralCase() {
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        assertEquals(3, hashMap.keys().size());
        assertTrue(hashMap.keys().contains("key1"));
        assertTrue(hashMap.keys().contains("key2"));
        assertTrue(hashMap.keys().contains("key3"));
    }

    @Test
    public void givenEmptyHashMapWhenPutNullKeyThenKeyListShouldContainNull() {
        hashMap.put(null, null);

        assertTrue(hashMap.keys().contains(null));
    }

    @Test
    public void testValuesGeneralCase() {
        hashMap.put("key1", "value1");
        assertEquals("[key1]", hashMap.keys().toString());
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        assertEquals(3, hashMap.keys().size());
        assertTrue(hashMap.values().contains("value1"));
        assertTrue(hashMap.values().contains("value2"));
        assertTrue(hashMap.values().contains("value3"));
    }

    @Test
    public void givenEmptyHashMapWhenPutNullKeyThenValuesListShouldContainNull() {
        hashMap.put(null, null);

        assertTrue(hashMap.values().contains(null));
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
        HashMap<String, String> extraHashMap = new HashMap<>();
        extraHashMap.put("KEY1", "VALUE1");
        extraHashMap.put("KEY2", "VALUE2");
        extraHashMap.put("KEY3", "VALUE3");
        extraHashMap.put("KEY4", "VALUE4");
        extraHashMap.put("KEY5", "VALUE5");
        extraHashMap.put("KEY6", "VALUE6");
        hashMap.putAll(extraHashMap);

        assertEquals(6, hashMap.size());
        assertEquals("VALUE1", hashMap.get("KEY1"));
        assertTrue(hashMap.containsKey("KEY2"));
        assertEquals("VALUE3", hashMap.remove("KEY3"));
    }
}
