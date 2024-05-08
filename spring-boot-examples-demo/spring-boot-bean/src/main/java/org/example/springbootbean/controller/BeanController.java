package org.example.springbootbean.controller;

import org.example.springbootbean.service.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {

    @Autowired
    private BeanService beanService;

    @RequestMapping("/test")
    public void testBean() {
        System.out.println("testBean " + beanService.getResponse());
    }
}
