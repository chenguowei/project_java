### Interceptor 作用

1. 日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算 PV（Page View）等；
2. 权限检查：如登录检测，进入处理器检测是否登录；
3. 性能监控：通过拦截器在进入处理器之前记录开始时间，在处理完后记录结束时间，从而得到该请求的处理时间。（反向代理，如 Apache 也可以自动记录）
4. 通用行为：读取 Cookie 得到用户信息并将用户对象放入请求，从而方便后续流程使用，还有如提取 Locale、Theme 信息等，只要是多个处理器都需要的即可使用拦截器实现。

### 自定义 Interceptor

如果你需要自定义 Interceptor 的话必须实现 org.springframework.web.servlet.HandlerInterceptor接口或继承 org.springframework.web.servlet.handler.HandlerInterceptorAdapter类，并且需要重写下面下面 3 个方法：

- preHandler(HttpServletRequest request, HttpServletResponse response, Object handler) 方法在请求处理之前被调用。该方法在 Interceptor 类中最先执行，用来进行一些前置初始化操作或是对当前请求做预处理，也可以进行一些判断来决定请求是否要继续进行下去。该方法的返回至是 Boolean 类型，当它返回 false 时，表示请求结束，后续的 Interceptor 和 Controller 都不会再执行；当它返回为 true 时会继续调用下一个 Interceptor 的 preHandle 方法，如果已经是最后一个 Interceptor 的时候就会调用当前请求的 Controller 方法。

- postHandler(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 方法在当前请求处理完成之后，也就是 Controller 方法调用之后执行，但是它会在 DispatcherServlet 进行视图返回渲染之前被调用，所以我们可以在这个方法中对 Controller 处理之后的 ModelAndView 对象进行操作。
- afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception ex) 方法需要在当前对应的 Interceptor 类的 postHandler 方法返回值为 true 时才会执行。顾名思义，该方法将在整个请求结束之后，也就是在 DispatcherServlet 渲染了对应的视图之后执行。此方法主要用来进行资源清理。



### 实例

#### 1.定义一个拦截器

```java
package org.example.springbootfilter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 在处理器处理请求之前执行
        System.out.println("=========执行MyInterceptor拦截器 preHandle =============");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在处理器请求完成后，返回 ModelAndView 之前执行
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("=========执行MyInterceptor posHandler ==============");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        // 在DispatchServlet完成处理完请求之后执行
        System.out.println("=========执行MyInterceptor afterCompletion ==============");
    }
}

```

#### 2.配置拦截器

```java
package org.example.springbootfilter.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConf implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                // 拦截路径
                .addPathPatterns("/filter/*");
    }
}
```



### Github

完整的代码：https://github.com/chenguowei/project_java/blob/main/spring-boot-examples-demo/spring-boot-filter/README.md
