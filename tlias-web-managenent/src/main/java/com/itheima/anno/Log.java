package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//表示可以在方法上使用
@Retention(RetentionPolicy.RUNTIME)//表示注解保留到运行时
public @interface Log {
}
