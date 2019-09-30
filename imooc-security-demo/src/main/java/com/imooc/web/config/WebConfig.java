package com.imooc.web.config;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: imooc-security
 * @description: Web配置类
 * @author: ouhuixuan
 * @create: 2019-09-04 09:24
 **/
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private TimeInterceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

//    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registration.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registration.setUrlPatterns(urls);
        return registration;
    }
}
