package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-08-31 11:58
 **/
@RestController
@RequestMapping("/user")
public class UserController {

   /* @GetMapping("/me")
    @ApiOperation(value = "获取当前登录用户的信息")
    public Object getCurrentUser(Authentication authentication){
        return authentication;
    }*/
   @GetMapping("/me")
   @ApiOperation("获取当前登录用户")
   public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
       return userDetails;
   }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户分页查询")
    public List<User> query(@ApiParam("用户名") @RequestParam(name = "username",defaultValue =  "tom",required = false) String nickName,
                            @PageableDefault(page = 1,size = 10,sort = {"username","desc"})Pageable pageable){
        System.out.println(nickName);
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
    /**
    * @Description: {id:\\d+}大括号中：后面是参数的正则表达式，只有参数符合表达式要求才能发起请求
    * @Param:  
    * @return:  
    * @Author: ouhuixuan
    * @Date: 2019/8/31 
    */
    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(name = "id") String id){
//       throw new UserNotExistException(id);
       System.out.println("进入getInfo方法");
        User user = new User();
        user.setUserName("tom");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
            user.setId("1");
            return user;
        }
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
            user.setId("2");
            return user;
        }
        user.setId("2");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable(name = "id") String ids){
        System.out.println(ids);
    }
}
