package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EnquiryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> findPage(EnquiryParam enquiryParam);

    void add(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp findById(Integer id);

    void alter(Emp emp);

    List<Emp> list();

    int findCountByDeptId(Integer id);
}
