package com.imooc.security.core.properties;

/**
 * @program: imooc-security
 * @description: 图像验证码参数
 * @author: ouhuixuan
 * @create: 2019-09-26 10:48
 **/
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int lenght) {
        this.length = lenght;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
