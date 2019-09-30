package com.imooc.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-09-04 09:44
 **/
//@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("控制器方法执行前执行该方法");
        System.out.println(((HandlerMethod)o).getBean().getClass().getName());
        System.out.println(((HandlerMethod)o).getMethod().getName());
        httpServletRequest.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("控制器方法执行后执行该方法");
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("TIME INTERCEPTOR 耗时："+(System.currentTimeMillis()-startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("销毁");
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("TIME INTERCEPTOR 耗时："+(System.currentTimeMillis()-startTime));
        System.out.println(e);
    }
}
