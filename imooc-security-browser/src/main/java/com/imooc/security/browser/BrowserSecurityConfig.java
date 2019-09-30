package com.imooc.security.browser;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @program: imooc-security
 * @description: 安全配置类
 * @author: ouhuixuan
 * @create: 2019-09-11 10:37
 **/
@Component
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @Description: 配置密码加密方式
     * @Param:
     * @return:
     * @Author: ouhuixuan
     * @Date: 2019/9/11
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter filter = new ValidateCodeFilter();
        filter.setImoocAuthencationFailHandle(imoocAuthenticationFailureHandler);
        filter.setSecurityProperties(securityProperties);
        filter.afterPropertiesSet();
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()//指定仅允许表单认证
                .loginPage("/authentication/require")//登录页面
                .loginProcessingUrl("/authentication/form")//登录url
                .successHandler(imoocAuthenticationSuccessHandler)//登录成功后处理行为
                .failureHandler(imoocAuthenticationFailureHandler)//登录失败后处理行为
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowserProperties().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()//表示下述的都是授权配置
                .antMatchers("/authentication/require", securityProperties.getBrowserProperties().getLoginPage(),
                        "/code/*").permitAll()
                .anyRequest()//表示任意请求
                .authenticated()//表示都需要身份认证
                .and()
                .csrf().disable();
    }
}
