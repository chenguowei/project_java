package org.example.servicedemo2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-provider")
public interface ProviderFeign {

    @GetMapping("/hello")
    public String hello();
}
