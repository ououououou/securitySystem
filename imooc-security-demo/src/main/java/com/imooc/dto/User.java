package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-08-31 11:59
 **/
public class User {
    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView{};

    @JsonView(UserSimpleView.class)
    @MyConstraint(message = "这是一个自定义校验规则")
    private String id;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @Past(message = "出生日期必须是过去的时间")
    private Date   birthday;
    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
@JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
