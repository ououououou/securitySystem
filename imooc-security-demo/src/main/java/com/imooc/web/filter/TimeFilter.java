package com.imooc.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-09-04 09:12
 **/
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TIME FILTER初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入TIME FILTER");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("TIME FILTER 结束总耗时："+(System.currentTimeMillis()-start));
        System.out.println("TIME FILTER结束");
    }

    @Override
    public void destroy() {
        System.out.println("TIME FILTER销毁");
    }
}
