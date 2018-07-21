package de.rieckpil.learning.javabycomparison.objectdesign;

import java.util.Stack;

public class StackType {

    public static void main(String[] args) {

        Stack<String> stringStack = new Stack<>();

        stringStack.push("123");
        stringStack.push("Hallo");
        stringStack.push("Was geht?");

        System.out.println(stringStack.pop());
        System.out.println(stringStack.pop());
        System.out.println(stringStack.peek());
        System.out.println(stringStack.pop());
        System.out.println(stringStack.empty());
    }
}
