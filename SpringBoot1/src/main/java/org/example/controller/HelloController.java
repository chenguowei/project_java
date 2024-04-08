package org.example.controller;

import org.example.OrderProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @Autowired
    private OrderProps orderProps;

//    public HelloController(OrderProps orderProps) {
//        this.orderProps = orderProps;
//    }

    @GetMapping("/")
    public String index() {

        return "Greetings from Spring Boot!, pageSize:" + orderProps.getPageSize() ;
    }

    @Autowired
    public void setOrderProps(OrderProps orderProps) {
        this.orderProps = orderProps;
    }
}
