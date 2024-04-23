package org.example.springbootfilter.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Order(2)
@WebFilter(filterName = "myFilter2", urlPatterns = {"/filter/*"})
public class MyFilter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化myFilter2过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入MyFilter2过滤器 ");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("MyFilter2处理一下服务端返回的response");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("销毁MyFilter2过滤器");
    }
}
