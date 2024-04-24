package org.example.springbootredis.controller;

import org.example.springbootredis.redisutil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/setKey")
    public void setKey(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        System.out.println("setKey key: " + key + ", value: " + value);
        redisUtil.set(key, value);
    }

    @PostMapping("/getKey")
    public String getKey(@RequestParam(value = "key") String key) {
        System.out.println("getKey key: " + key);
        Object value = redisUtil.get(key);

        return (String) value;
    }

    @PostMapping("/delKey")
    public void delKey(@RequestParam(value = "key") String key) {
        System.out.println("delKey key: " + key);
        redisUtil.del(key);
    }
}
