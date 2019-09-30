package com.imooc.security.browser.authentication;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.browser.support.SimpleRespone;
import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @program: imooc-security
 * @description: 处理登陆验证失败跳转，自定义失败后的自定义
 * @author: ouhuixuan
 * @create: 2019-09-17 17:09
 **/
@Component("imoocAuthenticationFailureHandler")
public class ImoocAuthencationFailHandle extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        logger.info("登录失败！");

        if (LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())){
            //设置登录失败返回状态码为500
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleRespone(e.getMessage())));
        }else{
            //父类默认方法，当登录验证失败后跳转回验证前的请求
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }
    }
}
