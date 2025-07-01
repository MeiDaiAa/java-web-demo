package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EnquiryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    EmpService empService;

    log.info("this message is code from github");

    /**
     * 分页查询员工信息
     */
    @GetMapping
    public Result findPage(EnquiryParam  enquiryParam){
        log.info("分页查询员工信息: {}",enquiryParam);

        PageResult<Emp> queryResult = empService.findPage(enquiryParam);

        return Result.success(queryResult);
    }


    /**
     * 新增员工
     */
    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info("添加员工: {}",emp);
        empService.add(emp);
        return Result.success();
    }


    /**
     * 删除员工
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工: {}",ids);

        empService.deleteByIds(ids);

        return Result.success();
    }

    /**
     * 根据id查询员工
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据id查询员工: {}",id);
        Emp emp = empService.findById(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    public Result alter(@RequestBody Emp emp){
        log.info("修改员工信息: {}",emp);
        empService.alter(emp);
        return Result.success();
    }
}
