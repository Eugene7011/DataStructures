package com.podzirey.datastructures;

import com.podzirey.datastructures.list.ArrayList;
import com.podzirey.datastructures.list.ArrayListTest;
import com.podzirey.datastructures.stack.ArrayStackTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Junit {
    public static void main(String[] args) throws Exception {
        runTests(ArrayListTest.class);

    }
    static void runTests(Class clazz) throws Exception {
        Constructor constructor = clazz.getConstructor();
        Object testInstance = constructor.newInstance();

        ArrayList testMethods = getTests(clazz);
        for (int i = 0; i < testMethods.size(); i++) {
            Method testMethod = (Method) testMethods.get(i);

            try{
                testMethod.invoke(testInstance);
                System.out.println("Test: " + testMethod.getName() + "success!");
            } catch (Throwable error){
                System.out.println("Test: " + testMethod.getName() + "failed!");
            }
        }

    }

    private static ArrayList getTests(Class clazz) {
        ArrayList methodsList = new ArrayList();
        for (Method method : clazz.getMethods()) {
            if(method.isAnnotationPresent(Test.class)){
                methodsList.add(method);
            }
        }
        return methodsList;
    }
}
