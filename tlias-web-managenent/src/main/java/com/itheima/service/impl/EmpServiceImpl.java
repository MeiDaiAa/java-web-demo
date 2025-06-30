package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> findPage(EnquiryParam enquiryParam) {
//        Long total = empMapper.findTotal();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.findPage(start, pageSize);
//
//        return new QueryResult<Emp>(total, rows);

        //pageHelper 插件实现分页查询,下面的这个方法不安全
//        Page<Emp> p = PageHelper.startPage(page, pageSize);
//
//        empMapper.findPage();
//
//        return new QueryResult<Emp>(p.getTotal(), p.getResult());

        //方法2：安全使用PageHelper
        PageHelper.startPage(enquiryParam.getPage(), enquiryParam.getPageSize());
        List<Emp> rows =empMapper.findPage(enquiryParam);
        Page<Emp> p2 = (Page<Emp>) rows;
        return new PageResult<Emp>(p2.getTotal(), rows);

    }

    @Override
    //添加事务
    @Transactional(rollbackFor = Exception.class)//设置回滚
    public void add(Emp emp) {
        try {
            //设置创建时间
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //添加到员工表
            empMapper.addEmp(emp);

            if(!emp.getExprList().isEmpty()){
                emp.getExprList().forEach(expr -> {
                    expr.setEmpId(emp.getId());
                });
            }
            //添加到员工经历表
            empMapper.addEmpExpr(emp.getExprList());
        } finally {
            //记录添加日志
            empLogService.insertLog(new EmpLog(null, LocalDateTime.now(), "添加员工: " + emp));
        }
    }

    /**
     * 通过id批量删除员工（包括员工工作经历）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)//由于要两次调用数据库所以要设置事务
    public void deleteByIds(List<Integer> ids) {
        //删除员工的基本信息
        empMapper.deleteByIds(ids);
        //删除员工的工作经历信息
        empMapper.deleteExprByIds(ids);
    }

    /**
     * 通过id查询员工
     */
    @Override
    public Emp findById(Integer id) {
        Emp emp = empMapper.findById(id);
        return emp;
    }

    /**
     * 修改员工信息
     */
    @Override
    public void alter(Emp emp) {
        //设置修改时间
        emp.setUpdateTime(LocalDateTime.now());
        //修改员工的基本信息
        empMapper.alter(emp);
        //删除员工旧的工作经历
        empMapper.deleteExprByIds(Collections.singletonList(emp.getId()));

        //如果员工有新增的工作经历
        List<EmpExpr> empExprList = emp.getExprList();
        if(!empExprList.isEmpty()){
            //将员工的id添加到每个工作经历中
            empExprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            empMapper.addEmpExpr(empExprList);
        }
    }
}
