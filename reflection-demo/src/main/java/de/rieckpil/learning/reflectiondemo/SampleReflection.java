package de.rieckpil.learning.reflectiondemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class SampleReflection {

    public static void main(String[] args) {

        Class<?> clazz = SampleClass.class;

        final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            ReflectionUtility.printConstructorInfos(constructor);
        }

        final Field[] fields = clazz.getDeclaredFields();

        System.out.println("Attributes: " + fields.length);

        for (Field field : fields) {
            ReflectionUtility.printFieldInfo(field);
        }


    }
}
