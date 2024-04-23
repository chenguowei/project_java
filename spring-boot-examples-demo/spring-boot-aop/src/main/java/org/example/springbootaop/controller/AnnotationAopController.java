package org.example.springbootaop.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.example.springbootaop.annotaion.PermissionsAnnotation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnotationAopController {

    @PostMapping("/aopTest")
    public String aopTest() {
        return "annotation aop";
    }

    @RequestMapping("/customAop")
    @PermissionsAnnotation
    public String customAop() {
        return "custom annotation aop";
    }
}
