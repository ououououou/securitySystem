package com.imooc.web.controller;


import com.imooc.dto.FileInfo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @program: imooc-security
 * @description: 文件上传下载服务器
 * @author: ouhuixuan
 * @create: 2019-09-05 19:49
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    private String folder = "D:\\IDE_workspace\\imooc-security\\imooc-security-demo\\src\\main\\java\\com\\imooc\\web\\controller";
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        File localFile = new File(folder,System.currentTimeMillis()+".txt");
        file.transferTo(localFile);
        return new FileInfo(folder);
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
            OutputStream outputStream = response.getOutputStream();
        ){
            response.setHeader("Content-Disposition","attachment;filename = test.txt");
            response.setContentType("application/x-download");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }
}

