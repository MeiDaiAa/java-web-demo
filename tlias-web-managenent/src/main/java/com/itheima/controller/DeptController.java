package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    //获取日志工具
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
    /**
     * 部门列表查询
     */
    @GetMapping
    public Result list() {
//        System.out.println("查询部门全部数据");
        log.info("查询部门全部数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    /**
     * 删除部门
     */
    @DeleteMapping
    public Result delete(Integer id) {
//        System.out.println("删除部门数据: " + id);
        log.info("删除部门数据: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }
    /**
     * 添加部门
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
//        System.out.println("添加部门数据: " + dept);
        log.info("添加部门数据: {}", dept);
        deptService.add(dept);
        return Result.success();
    }
    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
//        System.out.println("根据Id查询部门: " + id);
        log.info("根据Id查询部门: {}", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }
    /**
     * 修改部门
     */
    @PutMapping
    public Result alter(@RequestBody Dept dept){
//        System.out.println("修改部门数据: " + dept);
        log.info("修改部门数据: {}", dept);
        deptService.alter(dept);
        return Result.success();
    }
}
