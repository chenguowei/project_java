package com.testclass.io;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDemo {

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(1);

        // 1. 迭代遍历
        System.out.println("Iterator 遍历");
        Iterator<Integer> iterator = set1.iterator();
        while (iterator.hasNext()) {
            System.out.println("key:" + iterator.next());
        }

        // 2. for遍历
        System.out.println("for 遍历");
        for(Integer key : set1) {
            System.out.println("key:" + key);
        }

        // 3. forEach遍历
        System.out.println("forEach 遍历");
        set1.forEach(key -> System.out.println("key:" + key));
    }
}
