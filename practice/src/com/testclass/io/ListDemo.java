package com.testclass.io;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);


        // 1. 使用 for遍历
        System.out.println("for 遍历");
        for (Integer i : list) {
            System.out.println(i);
        }

        // 2.下标遍历
        System.out.println("下标遍历");
        for (int i =0; i < list.size(); i++) {
            System.out.println("i=" +i + ", value=" + list.get(i));
        }

        // 3.迭代器遍历
        System.out.println("迭代器遍历");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            System.out.println(i);
        }

        // 4. forEach遍历
        System.out.println("forEach遍历");
        list.forEach(System.out::println);
    }
}
