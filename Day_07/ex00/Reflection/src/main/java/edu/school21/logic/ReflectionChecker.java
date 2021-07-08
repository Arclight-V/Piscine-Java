package edu.school21.logic;

import java.lang.reflect.Field;

public class ReflectionChecker {
    public void showClassName(Object object) {
        Class clazz = object.getClass();
        System.out.println(clazz.getName());
    }

    public void showClassFields(Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            System.out.println(field.getName());
        }
    }
}
