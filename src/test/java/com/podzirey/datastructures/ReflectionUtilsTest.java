package com.podzirey.datastructures;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilsTest {

    @Test
    public void testCreateObjectBasedOnDefaultConstructor() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Object obj = ReflectionUtils.create(TestVo.class);

        assertNotNull(obj);
        //assertTrue(obj instanceof TestVo);
        assertEquals(TestVo.class, obj.getClass());
        TestVo testVo = (TestVo) obj;
        assertTrue(testVo.isCreatedWithDefaultConstructor);
        //assertFalse(testVo.isCreatedWithIntConstructor);
    }
}