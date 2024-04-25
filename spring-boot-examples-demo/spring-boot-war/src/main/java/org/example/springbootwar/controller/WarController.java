package org.example.springbootwar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarController {

    @GetMapping("/test")
    public String test() {
        return "War package test";
    }
}
