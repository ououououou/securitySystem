package com.imooc.security.core.validate.code.sms;

/**
 * @program: imooc-security
 * @description: 短信发送商接口
 * @author: ouhuixuan
 * @create: 2019-09-27 14:14
 **/
public interface SmsCodeSender {
    void send(String mobile,String code);
}
