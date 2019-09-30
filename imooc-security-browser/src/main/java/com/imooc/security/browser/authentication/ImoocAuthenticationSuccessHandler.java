package com.imooc.security.browser.authentication;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @program: imooc-security
 * @description: 处理登陆成功跳转,自定义登陆成功后的行为
 * @author: ouhuixuan
 * @create: 2019-09-17 16:23
 **/
@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;
    /**
    * @Description: 登录认证成功后调用该方法
    * @Param:  authentication封装了认证信息 ，里面包括了发起的请求内的认证信息，如请求IP，session，以及请求通过后Userdetails信息
    * @return:
    * @Author: ouhuixuan
    * @Date: 2019/9/17
    */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        if (LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            //把登陆成功后包含用户信息的authentication写回去
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            //父类默认方法，跳转回验证成功前的请求页面
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }


    }
}
