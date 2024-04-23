# Getting Started

## SpringBoot AOP的实践

### AOP相关的概念：

- Aspect（切面）： Aspect 声明类似于 Java 中的类声明，在 Aspect 中会包含着一些 Pointcut 以及相应的 Advice。就是抽离出来的逻辑类，比如日志、权限等。

- Joint point（连接点）：表示在程序中明确定义的点，典型的包括方法调用，对类成员的访问以及异常处理程序块的执行等等，它自身还可以嵌套其它 joint point。
- Pointcut（切点）：表示一组 joint point，这些 joint point 或是通过逻辑关系组合起来，或是通过通配、正则表达式等方式集中起来，它定义了相应的 Advice 将要发生的地方。定义需要拦截的函数。
- Advice（增强）：Advice 定义了在 Pointcut 里面定义的程序点具体要做的操作，它通过 before、after 和 around 来区别是在每个 joint point 之前、之后还是代替执行的代码。
- Target（目标对象）：织入 Advice 的目标对象.。
- Weaving（织入）：将 Aspect 和其他对象连接起来, 并创建 Adviced object 的过程



![这里写图片描述](/Users/fanjiaqi/workspaces/project_java/spring-boot-examples-demo/spring-boot-aop/src/main/resources/static/70.png)

### Advice 的类型

- before advice, 在 join point 前被执行的 advice. 虽然 before advice 是在 join point 前被执行, 但是它并不能够阻止 join point 的执行, 除非发生了异常(即我们在 before advice 代码中, 不能人为地决定是否继续执行 join point 中的代码)

- after return advice, 在一个 join point 正常返回后执行的 advice

- after throwing advice, 当一个 join point 抛出异常后执行的 advice
- after(final) advice, 无论一个 join point 是正常退出还是发生了异常, 都会被执行的 advice.
- around advice, 在 join point 前和 joint point 退出后都执行的 advice. 这个是最常用的 advice.
  introduction，introduction可以为原有的对象增加新的属性和方法。



### 使用 execution()

使用 execution 指定一个切点，就是定义需要拦截的东西。

1.定义一个切面

```java
package org.example.springbootaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 切面
@Aspect
@Component
public class AopAdvice {

    // 定义切点
    @Pointcut("execution(* org.example.springbootaop.controller.*.*(..))")
    public void test() {}

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
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("beforce");

        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.out.println("after");
    }
}

```



execution(* org.example.springbootaop.controller.\*.\*(..))

第一个*代表方法返回值类型，第二个\*代表任意类，第三个\*代表任意方法，(..)代表任意参数类型。



### 使用 annotation()

1.自定义一个注解

```
package org.example.springbootaop.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionsAnnotation {
}
```

2.定义一个切面

```java
package org.example.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomAnnAopAdvice {
		// 指定注解所在的包
    @Pointcut("@annotation(org.example.springbootaop.annotaion.PermissionsAnnotation)")
    private void permissionLog(){}

    @Before("permissionLog()")
    public void beforeAdvice() {
        System.out.println("custom annotation aop before");
    }
}
```

3.在对应的函数上使用注解

```java
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
    @PermissionsAnnotation   // 只用自定义的注解
    public String customAop() {
        return "custom annotation aop";
    }
}
```

使用以存在的注解，比如 GetMapping 也是这样使用，只不过 PointCut里指定GetMapping的注解包路径。
