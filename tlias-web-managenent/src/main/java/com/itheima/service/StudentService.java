package com.itheima.service;

import com.itheima.pojo.EnquiryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;

import java.util.List;

public interface StudentService {
    PageResult<Student> getPage(EnquiryParam student);

    void add(Student student);

    Student findById(Integer id);

    void alter(Student student);

    void deleteByIds(List<Integer> list);

    void updateViolation(Integer id, Integer score);
}
