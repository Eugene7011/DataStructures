package com.podzirey.datastructures;

import java.lang.reflect.Field;
import java.util.Random;

public class MessagePrinter {
    String message;
    @InjectRandom
    int count;

    public void print(){
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class AnnotationTest{
    public static void main(String[] args) throws IllegalAccessException {
        MessagePrinter messagePrinter = new MessagePrinter();
        messagePrinter.setMessage("Hello Reflection");


        injectRandomProcessor(messagePrinter);
        messagePrinter.print();
    }

    static void  injectRandomProcessor(Object value) throws IllegalAccessException {
        Class clazz = value.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            if(declaredField.isAnnotationPresent(InjectRandom.class)){
                Random random = new Random();
                int randomValue = random.nextInt(10);

                declaredField.set(value, randomValue);
            }
        }
    }
}
