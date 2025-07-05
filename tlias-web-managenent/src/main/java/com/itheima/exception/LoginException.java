package com.itheima.exception;

/**
 * 自定义登录异常类
 */
public class LoginException extends RuntimeException{
    public LoginException(String message){
        super(message);
    }
}
