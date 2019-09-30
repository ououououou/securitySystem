package com.imooc.security.core.validate.impl;

import com.imooc.security.core.validate.ValidateCodeProcessor;
import com.imooc.security.core.validate.ValidateCodeType;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeException;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-09-27 15:12
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    /** 
    * 操作session的工具类
    */ 
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * 收集系统中所有{@Link ValidateCodeGenerator} 接口的实现
     */
    private Map<String,ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }


    /**
     * @Description: 生成校验码
     * @Param:  ServletWebRequest servlet的工具类
     * @return:  C
     * @Author: ouhuixuan
     * @Date: 2019/9/27
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request){
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null){
            throw new ValidateCodeException("验证码生成器"+generatorName+"不存在");
        }
        return (C)validateCodeGenerator.generate(request);
    }
    /**
    * @Description: 保存验证码
    * @Param:
    * @return:
    * @Author: ouhuixuan
    * @Date: 2019/9/27
    */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, getSessionKey(request), validateCode);
    }
    /**
    * @Description: 发送验证码
    * @Param:
    * @return:
    * @Author: ouhuixuan
    * @Date: 2019/9/27
    */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;
    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }


    /** 
    * @Description: 根据请求的url获取校验码的类型
    * @Param:  
    * @return:  
    * @Author: ouhuixuan
    * @Date: 2019/9/27 
    */ 
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(),"CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }
}
