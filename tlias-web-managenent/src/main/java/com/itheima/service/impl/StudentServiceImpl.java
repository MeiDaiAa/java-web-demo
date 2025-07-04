package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.EnquiryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 学员列表数据的条件分页查询
     */
    @Override
    public PageResult<Student> getPage(EnquiryParam student) {
        PageHelper.startPage(student.getPage(),student.getPageSize());
        List<Student> students = studentMapper.getPage(student);
        Page<Student> p = (Page<Student>) students;

        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    /**
     * 添加学员
     */
    @Override
    public void add(Student student) {
        //添加 时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    /**
     * 根据id查询学员
     */
    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    /**
     * 修改学员
     */
    @Override
    public void alter(Student student) {
        //修改更新时间
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.alter(student);
    }

    /**
     * 批量删除学员
     */
    @Override
    public void deleteByIds(List<Integer> list) {
        studentMapper.deleteByIds(list);
    }

    /**
     * 修改学员的违纪信息
     */
    @Override
    public void updateViolation(Integer id, Integer score) {
        //先查询到学员的信息
        Student student = studentMapper.findById(id);
        log.info("将学生的违纪信息从：{}，{} 修改为 {}，{}", student.getViolationCount(), student.getViolationScore(),
                                                        student.getViolationCount() + 1, student.getViolationScore() + score);

        studentMapper.updateViolation(id, student.getViolationCount() + 1, student.getViolationScore() + score);
    }
}
