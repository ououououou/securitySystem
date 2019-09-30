package com.imooc.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-08-30 15:38
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController {
    @Autowired
    private WebApplicationContext webApplicationContext;
    //伪造mvc环境
    private MockMvc mockMvc;

    //每次启动测试前调用的方法：
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(
                //建立伪造的请求
                MockMvcRequestBuilders.get("/user")
                        // .param("username","jojo")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                //期望返回状态码为成功
                .andExpect(MockMvcResultMatchers.status().isOk())
                //期望返回数据集合元素的数量（即集合长度）
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void whenGenInfoSuccess() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("tom"));
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/a")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        String content = "{\"userName\":\"tom\",\"password\":null}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id")
                .value("1"));
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        //拿到当前日期加一年后的日期（精确到毫秒）
        Date birthday = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(birthday);
        /*//拿到当前日期加一年后的日期（精确到毫秒）
        Date birthday = new Date(LocalDateTime.now().);*/
        String content = "{\"id\":\"1\",\"userName\":\"tom\",\"password\":\"123456\",\"birthday\":"+birthday.getTime()+"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"));
    }

    @Test
    public void whenDeleteSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUploadSuccess() throws Exception {
      String result =  mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
                .file(new MockMultipartFile("file"
        ,"test.txt","multipart/form-data","hello upload".getBytes())))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andReturn().getResponse().getContentAsString();
      System.out.println(result);
    }
}
