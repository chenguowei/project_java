package org.example.springbootresilience4j.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/r4j")
public class R4jController {

    private final RestTemplate restTemplate;

    public R4jController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/limit")
    public String limit() {


        return "ok";
    }

    @GetMapping("circuit-breaker")
    @CircuitBreaker(name = "circuitBreakerDemo")
    public String circuitBreaker() {
        return restTemplate.getForObject("http://no-such-site/api/external", String.class);
    }
}
