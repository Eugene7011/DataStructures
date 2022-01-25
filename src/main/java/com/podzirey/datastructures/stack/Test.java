package com.podzirey.datastructures.stack;

import com.podzirey.datastructures.list.ArrayList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Class clazz = Person.class;
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field.getGenericType());
//            System.out.println(field);
//        }

//        Method printNameMethod = clazz.getMethod("printName");
//        System.out.println(printNameMethod);
//
//        Person person = new Person();
//        person.name = "Tolik";
//        printNameMethod.invoke(person);

        ArrayList arrayList = new ArrayList();
        Class clazz = arrayList.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            System.out.println(declaredField);
            if(declaredField.getName().equals("size2")){
                declaredField.setAccessible(true);
                declaredField.set(arrayList, -100_000);
            }
        }
        //System.out.println(arrayList.getSize2());

    }
    static void print (int prefix, String... array){
        for (String s: array) {
            System.out.println(prefix + s);
        }
    }
}

class Person{
    String name;
    double salary;
    public int age;

    public void printName(){
        System.out.println("my name is :" + name);
    }

    public void printName(int count){
        for (int i = 0; i < count; i++) {
            System.out.println("my name is :" + name);
        }

    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }
}