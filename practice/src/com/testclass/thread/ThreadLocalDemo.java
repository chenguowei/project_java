package com.testclass.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalDemo {

    public static void main(String[] args) {

        List<ThreadLocal<Integer>> threadLocalList = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            ThreadLocal<Integer> local = new ThreadLocal<>();
            local.set(i);
            threadLocalList.add(local);
        }

        for (ThreadLocal<Integer> local : threadLocalList) {
            System.out.println("local: " + local.get());
        }

        testGetFirst();
    }

    public  static void testGetFirst() {
        ThreadLocal<Integer> local = new ThreadLocal<>();
        System.out.println("first get: " + local.get());
    }
}
