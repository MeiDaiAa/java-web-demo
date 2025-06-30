package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class MultipartController {
    //本地存储文件
/*    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file){
        log.info("上传文件: {}, {}, {}", name, age, file);
//        String originalFilename = file.getOriginalFilename();
//        log.info("文件名: {}", originalFilename);
        //保存文件
        if(!file.isEmpty()){
            try {
                //获取文件后缀
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                log.info("文件后缀: {}", suffix);
                //生成随机文件名
                String filename = UUID.randomUUID().toString().replace("-", "") + suffix;
                //文件本地存储
                file.transferTo(new File("E:/develop/image/" + filename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.success();
    }*/
    //将文件上传到阿里云
    @Autowired
    AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        log.info("上传文件: {}", file);
        String url = "";
        try {
            url = aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("文件上传成功url: {}", url);
        return Result.success(url);
    }
}
