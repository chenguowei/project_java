package org.example.servicedemo2.controller;

import io.micrometer.observation.annotation.Observed;
import org.example.servicedemo2.feign.ProviderFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Observed
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderFeign providerFeign;

    private final Logger log = LoggerFactory.getLogger(HelloController.class);

//    private final String ServiceProvider = "http://localhost:18080";

    private final String ServiceProvider = "http://service-provider";

    @RequestMapping("/hello")
    public String hello() {
        log.info("HelloController hello request start");
//        return restTemplate.getForObject(ServiceProvider+"/hello", String.class);
        return providerFeign.hello();
    }
}
