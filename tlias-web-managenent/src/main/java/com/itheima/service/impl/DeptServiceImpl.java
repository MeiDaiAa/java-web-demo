package com.itheima.service.impl;

import com.itheima.exception.DeptException;
import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements com.itheima.service.DeptService{

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 删除部门
     * 如果部门下有员工，则不允许删除该部门，并给前端提示错误信息：对不起，当前部门下有员工，不能直接删除！
     */
    @Autowired
    private EmpService empService;
    @Override
    public void deleteById(Integer id) {
        int count = empService.findCountByDeptId(id);
        if(count > 0) throw new DeptException("对不起，当前部门下有员工，不能直接删除！");

        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.add(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void alter(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.alter(dept);
    }
}
