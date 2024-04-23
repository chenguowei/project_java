package org.example.springbootaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }
}
