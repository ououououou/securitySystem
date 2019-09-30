package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: imooc-security
 * @description: 验证码生成接口
 * @author: ouhuixuan
 * @create: 2019-09-27 10:00
 **/
public interface ValidateCodeGenerator {
        ValidateCode generate(ServletWebRequest request);
}
