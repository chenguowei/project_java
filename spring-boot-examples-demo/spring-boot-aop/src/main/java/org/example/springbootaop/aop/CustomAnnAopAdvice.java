package org.example.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomAnnAopAdvice {

    @Pointcut("@annotation(org.example.springbootaop.annotaion.PermissionsAnnotation)")
    private void permissionLog(){}

    @Before("permissionLog()")
    public void beforeAdvice() {
        System.out.println("custom annotation aop before");
    }
}
