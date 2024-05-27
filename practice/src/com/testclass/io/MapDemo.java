package com.testclass.io;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapDemo {

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("test1", "value1");
        map1.put("test2", "value2");

        // 1. 按 entry遍历
        for (Map.Entry<String, String> entity : map1.entrySet()) {
            System.out.println("key=" + entity.getKey() + ", value=" + entity.getValue());
        }

        // 2. key, value分别遍历
        for (String key : map1.keySet()) {
            System.out.println("key=" + key);
        }
        for (String val : map1.values()) {
            System.out.println("value=" + val);
        }

        // 3. Iterator遍历
        Iterator<Map.Entry<String,String>> iterator = map1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> one = iterator.next();
            System.out.println("key=" + one.getKey() + ", value=" + one.getValue());
        }
    }
}
