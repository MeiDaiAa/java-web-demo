package com.itheima.controller;


import com.itheima.pojo.Clazz;
import com.itheima.pojo.EnquiryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    ClazzService clazzService;
    /**
     * 班级列表数据的条件分页查询
     */
    @GetMapping
    public Result findPage(EnquiryParam enquiryParam){
        log.info("班级列表分页查询: " + enquiryParam.toString());

        PageResult<Clazz> pageResult = clazzService.findPage(enquiryParam);

        return Result.success(pageResult);
    }
    /**
     * 删除班级
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级: " + id);

        clazzService.deleteById(id);

        return Result.success();
    }
    /**
     * 添加班级
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级: " + clazz);

        clazzService.add(clazz);

        return Result.success();
    }

    /**
     * 根据id查询班级
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据id查询班级: " + id);
        Clazz clazz = clazzService.findById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级
     */
    @PutMapping
    public Result alter(@RequestBody Clazz clazz){
        log.info("修改班级: " + clazz);
        clazzService.alter(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级信息");
        List<Clazz> list = clazzService.list();
        return Result.success(list);
    }
}
