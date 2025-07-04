package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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
    public int deleteById(Integer id) {
        List<Emp> emps = empService.list();

        for(Emp emp : emps){
            //注意比较不能使用 == 来比较，因为 == 比较的是地址，在-128 ~ 127 的时候是缓存的，虽然相等但是如果超出范围就不会相等
            if(Objects.equals(emp.getDeptId(), id)) return -1;
        }

        deptMapper.deleteById(id);
        return 0;
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
