package com.podzirey.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayListTest {

    @Test
    public void testLastIndexOf(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        assertEquals(4, arrayList.lastIndexOf(4));
    }

//    @Test
//    public void testTrowIndexBoundsOfOutExceptionWithLargerIndex(){
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(1);
//        Asseertions.assertThrows(IndexOutOfBoundsException.class, () -> {
//            arrayList.add(2, 2);
//        });
//    }

    @Test
    public void testVerifyToString(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        assertEquals("[1, 2, 3]", arrayList.toString());
    }
}
