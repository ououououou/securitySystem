package com.imooc.security.core.properties;

/**
 * @program: imooc-security
 * @description: 图像验证码参数
 * @author: ouhuixuan
 * @create: 2019-09-26 10:48
 **/
public class ImageCodeProperties extends SmsCodeProperties{
    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;
    private int height = 23;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
