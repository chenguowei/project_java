package org.example.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAopAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void logAnnotation() {}



    @Before("logAnnotation()")
    public void logAdvice() {
        System.out.println("annotation aop before");
    }
}
