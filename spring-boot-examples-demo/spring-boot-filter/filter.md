## Filter生命周期

- 程序启动调用Filter的init()方法(永远只调用一次)；
- 程序停止调用Filter的destroy()方法(永远只调用一次)；
- doFilter()方法每次的访问请求如果符合拦截条件都会调用(程序第一次运行，会在servlet调用init()方法以后调用；
- 不管第几次，都在调用doGet(),doPost()方法之前)。



## Filter的使用

无论哪种方式都需要先定义一个Filter

```java
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化myFilter过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入MyFilter过滤器 ");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("MyFilter处理一下服务端返回的response");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("销毁MyFilter过滤器");
    }
}
```

### 一、注解配置

```java
package org.example.springbootfilter.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Order(1)
@WebFilter(filterName = "myFilter1", urlPatterns = {"/filter/*"})
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化myFilter过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入MyFilter过滤器 ");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("MyFilter处理一下服务端返回的response");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("销毁MyFilter过滤器");
    }
}
```

代码说明：

 1.**@WebFilter**注解，**filterName**属性表示filter的名称，**urlPatter**表示要拦截的URL资源，可以是一个或者多个。

 2.**@Order(1)**表示如果有多个拦截器的话就是设置这个拦截器的运行等级，数字越小，越先执行

 3.**init()**方法只会执行一次，初始化过滤器。

 4.**doFilter()**核心方法，配置过滤器的逻辑代码。

 5.**destroy()**只会在项目停止或者是项目重新部署的时候才会执行。



开启扫描配置：

```java
package org.example.springbootfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringBootFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFilterApplication.class, args);
    }
}
```

 配置完上面的之后我们还需要在启动类加上一个扫描包的注解，开启包扫描。**@ServletComponentScan("com.example.springbootfilter.filter")**，当然你也可以不用写包的具体地址，不传参数，但是建议是传参数，并且这个采参数也可以传多个的。

 以上就完成了一个Filter的基本配置，运行项目即可看到效果，还有一种非注解形式的配置方式。

### 二、Java配置

使用 @Configuration + @Bean配置。

```java
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("Filter1");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
```



### Github:

完整的代码github: https://github.com/chenguowei/project_java/blob/main/spring-boot-examples-demo/spring-boot-filter/README.md