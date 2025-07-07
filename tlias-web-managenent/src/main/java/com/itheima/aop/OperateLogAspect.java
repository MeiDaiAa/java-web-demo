package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        long start = System.currentTimeMillis();


        Object result = joinPoint.proceed();

        //记录结束时间
        long end = System.currentTimeMillis();

        OperateLog operateLog = new OperateLog(
                null,
                CurrentHolder.getCurrentId(),//通过ThreadLocal获取当前登录用户的id
                LocalDateTime.now(),
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs().toString(),
                result.toString(),
                end - start);

        //添加到数据库
        log.info("记录操作日志：{}", operateLog);
        operateLogMapper.insert(operateLog);

        return result;
    }

}
