package com.itheima.controller;


import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 处理数据报表类的请求
 */
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    /**
     * 员工职位人数统计
     */
    @GetMapping("/empJobData")
    public Result empjobData(){
        log.info("员工职位人数统计");

        List<Map<String, Object>> empjobList = reportService.empJobData();

        List<String> jobList = empjobList.stream().map(x-> (String)x.get("pos")).collect(Collectors.toList());
        List<Integer> dataList = empjobList.stream().map(x -> ((Number)x.get("total")).intValue()).collect(Collectors.toList());


        return Result.success(new JobOption(jobList, dataList));
    }

    /**
     * 员工性别统计
     */
    @GetMapping("/empGenderData")
    public Result empGenderData(){
        log.info("员工性别统计");
        List<Map<String, Object>> empGenderList = reportService.empGenderData();

        return Result.success(empGenderList);
    }
}
