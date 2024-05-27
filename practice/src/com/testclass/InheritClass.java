package com.testclass;

public class InheritClass extends Father{
    private String name = "test";
    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Father inheritClass = new InheritClass();
        System.out.println(inheritClass.getName());
    }
}


class Father {
    private String name = "father";

    public String getName() {
        return name;
    }
}
