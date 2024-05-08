package org.example.servicedemo2.config;

import io.micrometer.tracing.Tracer;
import io.opentelemetry.api.trace.TracerBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import zipkin2.internal.Trace;

import java.nio.charset.Charset;

@Configuration
public class RestTemplateConfig {

    //    private final Tracer tracer;
//
//    RestTemplateConfig(Tracer tracer) {
//        this.tracer = tracer;
//    }
//
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restTemplate;
    }

//    @Bean
//    RestTemplate restTemplate(RestTemplateBuilder builder) {
//        RestTemplate restTemplate = builder.build();
//        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//
//        return restTemplate;
//    }


}
