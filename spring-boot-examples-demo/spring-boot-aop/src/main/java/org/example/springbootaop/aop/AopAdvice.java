package org.example.springbootaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


// 切面
@Aspect
@Component
public class AopAdvice {

    // 定义切点
    @Pointcut("execution(* org.example.springbootaop.controller.AopController.*(..))")
    public void test() {

    }

    // 通知：advice
    @Before("test()")
    public void beforeAdvice() {
        System.out.println("before advice...");
    }

    @After("test()")
    public void afterAdvice() {
        System.out.println("afterAdvice...");
    }

    @AfterReturning("test()")
    public void afterReturn() {
        System.out.println("afterReturn....");
    }

    @Around("test()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("around beforce");
        Object result = new Object();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.out.println("around after");

        return result;
    }





}
