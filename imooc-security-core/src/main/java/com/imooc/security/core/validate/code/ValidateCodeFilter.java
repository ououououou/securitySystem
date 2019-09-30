package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: imooc-security
 * @description: 自定义拦截器，用于拦截图形验证码的校验，在usernameandpasswordAuthenticationFilter之前
 * OncePerRequestFilter:spring提供的工具类，用于保证该拦截器只被调用一次
 * @author: ouhuixuan
 * @create: 2019-09-23 16:52
 **/
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private AuthenticationFailureHandler imoocAuthencationFailHandle;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private SecurityProperties securityProperties;
    private Set<String> urls = new HashSet<>();
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //application.properties配置的那些URL
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidateCodeProperties().getImageCodeProperties().getUrl(),",");
        if (configUrls != null && configUrls.length >0){
            for (String url:configUrls) {
                urls.add(url);
            }
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url:urls){
            if (pathMatcher.match(url,httpServletRequest.getRequestURI())){
                action = true;
            }
        }
        if(action){
            try{
                validate(new ServletWebRequest(httpServletRequest));
            }catch (ValidateCodeException e){
                imoocAuthencationFailHandle.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException{

        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
        if (StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("请输入验证码");
        }
        if (codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码错误");
        }
        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
    }
    public AuthenticationFailureHandler getImoocAuthencationFailHandle() {
        return imoocAuthencationFailHandle;
    }

    public void setImoocAuthencationFailHandle(AuthenticationFailureHandler imoocAuthencationFailHandle) {
        this.imoocAuthencationFailHandle = imoocAuthencationFailHandle;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
