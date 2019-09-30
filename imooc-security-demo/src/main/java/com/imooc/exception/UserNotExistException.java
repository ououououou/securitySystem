package com.imooc.exception;

/**
 * @program: imooc-security
 * @description: 自定义异常类
 * @author: ouhuixuan
 * @create: 2019-09-03 19:12
 **/
public class UserNotExistException extends RuntimeException {
    private static final long serialVersionUID = -6112780192479692859L;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserNotExistException(String id){
        super("user not exist!");
        this.id = id;
    }
}
