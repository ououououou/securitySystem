package com.imooc.security.core.validate.code.sms;

/**
 * @program: imooc-security
 * @description: 默认短信发送商实现
 * @author: ouhuixuan
 * @create: 2019-09-27 14:16
 **/
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向"+mobile+"发送验证码"+code);
    }
}
