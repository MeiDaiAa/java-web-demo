package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result getPage(EnquiryParam  student){
        log.info("分页查询参数: {}",student);

        PageResult<Student> pageResult = studentService.getPage(student);

        return Result.success(pageResult);
    }
    /**
     * 添加学员信息
     */
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学员信息: {}",student);

        studentService.add(student);

        return Result.success();
    }

    /**
     * 根据id查询
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据id查询学生: " + id);

        Student student = studentService.findById(id);

        return Result.success(student);
    }

    /**
     * 修改学员信息
     */
    @PutMapping
    public Result alter(@RequestBody Student student){
        log.info("修改学员信息: {}",student);

        studentService.alter(student);

        return Result.success();
    }

    /**
     * 删除学员信息
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Integer[] ids){
        List<Integer> list = Arrays.asList(ids);
        log.info("删除学员信息: " + list);

        studentService.deleteByIds(list);

        return Result.success();
    }

    /**
     * 修改学员的违纪数据信息
     */
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("处理学生违纪信息: {}, {}", id, score);

        studentService.updateViolation(id, score);

        return Result.success();
    }

}
