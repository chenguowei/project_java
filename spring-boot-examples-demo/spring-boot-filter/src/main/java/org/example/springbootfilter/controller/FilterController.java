package org.example.springbootfilter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter")
public class FilterController {

    @RequestMapping("/getFilter")
    public String getFilter() {
        System.out.println("getFilter exec ");
        return "get filter";
    }
}
