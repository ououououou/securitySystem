package com.imooc.security.browser.support;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-09-16 09:19
 **/

public class SimpleRespone {
    private String content;

    public SimpleRespone(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
