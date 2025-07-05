package com.itheima.exception;


/**
 * 自定义部门异常类
 */
public class DeptException extends RuntimeException{
    public DeptException(String message){
        super(message);
    }
}
