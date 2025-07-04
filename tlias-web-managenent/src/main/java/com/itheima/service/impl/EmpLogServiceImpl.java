package com.itheima.service.impl;

import com.itheima.mapper.EmpLogMapper;
import com.itheima.pojo.EmpLog;
import com.itheima.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;
    
    @Override
    //添加事务，并设置事务传播行为为新启一个事务，防止上一个事务回滚时回滚当前事务
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)//
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
