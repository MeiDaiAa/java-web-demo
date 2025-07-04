package com.itheima.controller;

import com.itheima.exception.BusinessException;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result exceptionHandler(Exception e){
        log.error("出现异常：",e);
        return Result.error("出现异常，请联系管理员");
    }

    @ExceptionHandler
    public Result DuplicateKeyExceptionHandler(DuplicateKeyException e){
        log.error("出现唯一异常： ",e);

        String message = e.getMessage();
        if (message.contains("Duplicate entry")){
            message = message.substring(message.indexOf("Duplicate entry"));
            String[] split = message.split(" ");
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }

        return Result.error("DuplicateKeyException");
    }

    @ExceptionHandler
    public Result BusinessExceptionHandler(BusinessException e){
        log.error("部门管理出现异常：", e);
        return Result.error(e.getMessage());
    }
}
