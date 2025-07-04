package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     */
    public List<Dept> findAll();


    /**
     * 根据id删除部门
     */
    public int deleteById(Integer id);

    /**
     * 添加部门
     */
    public void add(Dept dept);

    /**
     * 根据ID查询部门
     */
    public Dept findById(Integer id);

    /**
     * 修改部门数据
     */
    public void alter(Dept dept);
}
