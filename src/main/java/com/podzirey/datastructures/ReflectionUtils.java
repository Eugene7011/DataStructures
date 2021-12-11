package com.podzirey.datastructures;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {
    //Метод принимает класс и возвращает созданный объект этого класса

    static Object create(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Field field = clazz.getDeclaredField("isCreatedWithDefaultConstructor");
        System.out.println(field.getType());
        Field field2 = clazz.getDeclaredField("testList");
        System.out.println(field2.getType());

        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            if(constructor.getParameterCount() == 0){
                Object result = constructor.newInstance();
                return result;
            }
        }
        throw new IllegalArgumentException("Class doesn't have default constructor");
    }


}
