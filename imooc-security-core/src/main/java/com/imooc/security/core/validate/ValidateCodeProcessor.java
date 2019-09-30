package com.imooc.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: imooc-security
 * @description: 校验码处理器，封装不同校验码的处理逻辑
 * @author: ouhuixuan
 * @create: 2019-09-27 15:06
 **/
public interface ValidateCodeProcessor {
    /** 
    * @Description: 验证码放入session时的前缀
    * @Param:  
    * @return:  
    * @Author: ouhuixuan
    * @Date: 2019/9/27 
    */ 
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    /** 
    * @Description: 创建校验码 
    * @Param:  
    * @return:  
    * @Author: ouhuixuan
    * @Date: 2019/9/27 
    */ 
    void create(ServletWebRequest request) throws Exception;
}
