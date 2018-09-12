package de.rieckpil.learning.reflectiondemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

public class ReflectionUtility {

    public static void printConstructorInfos(final Constructor<?> ctor) {

        Objects.requireNonNull(ctor, "Constructor must not be empty");

        System.out.println("Modifier: " + Modifier.toString(ctor.getModifiers()));
        System.out.println("Name: " + ctor.getName());
        System.out.println("Amount of parameters: " + ctor.getParameterCount());
        System.out.println("Parameter Types: " + Arrays.toString(ctor.getParameterTypes()));
        System.out.println("Annotations: " + Arrays.toString(ctor.getAnnotations()));
        System.out.println("------------------------------");

    }

    public static void printFieldInfo(final Field field) {

        Objects.requireNonNull(field, "Field must not be empty");

        System.out.println("Generic Type: " + field.getGenericType().getTypeName());
        System.out.println("Modifier: " + Modifier.toString(field.getModifiers()));
        System.out.println("Name: " + field.getName());
        System.out.println("Annotations: " + Arrays.toString(field.getAnnotations()));
        System.out.println("------------------------------");

    }
}
