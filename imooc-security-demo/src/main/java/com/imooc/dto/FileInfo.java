package com.imooc.dto;

/**
 * @program: imooc-security
 * @description: 文件上传下载实体
 * @author: ouhuixuan
 * @create: 2019-09-05 19:53
 **/
public class FileInfo {
    private String path;
    public FileInfo(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
