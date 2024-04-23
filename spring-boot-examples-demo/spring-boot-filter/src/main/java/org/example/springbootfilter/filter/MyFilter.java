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
