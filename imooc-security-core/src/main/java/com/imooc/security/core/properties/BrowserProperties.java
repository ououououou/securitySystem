package com.imooc.security.core.properties;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-09-16 16:54
 **/
public class BrowserProperties {
    //此处给出默认值，如果用户没有在application。xml内配置值，则跳转此默认值
    private String loginPage = "/imooc-signIn.html";
    //此处给出默认值，如果用户没有在application。xml内配置值，则当登录成功或失败后的处理行为是返回一个JSON格式内容
    private LoginType loginType = LoginType.JSON;
    //记住我功能的cookie存活时间
    private int rememberMeSeconds = 360000;
    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getLoginPage() {
        return loginPage;
    }
    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
