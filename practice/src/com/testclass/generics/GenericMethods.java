package com.testclass.generics;

public class GenericMethods {

    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods g = new GenericMethods();
        g.f("");
        g.f(1);
        g.f(g);
    }
}
