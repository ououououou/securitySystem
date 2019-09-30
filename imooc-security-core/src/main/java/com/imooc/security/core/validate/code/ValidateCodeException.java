package com.imooc.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @program: imooc-security
 * @description: 自定义验证码异常，继承springSecurity提供的AuthenticationException
 * @author: ouhuixuan
 * @create: 2019-09-24 14:56
 **/
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
