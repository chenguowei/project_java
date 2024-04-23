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
