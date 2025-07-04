package com.itheima.service.impl;

import com.itheima.mapper.ReportMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReprotServiceImpl implements ReportService {
    @Autowired
    ReportMapper reportMapper;

    /**
     *  统计员工职位人数
     */
    @Override
    public List<Map<String, Object>> empJobData() {
        List<Map<String, Object>> empjobList = reportMapper.empJobData();

        return empjobList;
    }

    /**
     * 统计员工性别
     */
    @Override
    public List<Map<String, Object>> empGenderData() {

        return reportMapper.empDenderData();
    }

    /**
     * 班级人数统计
     */
    @Override
    public ClazzOption studentCountData() {
        List<Map<String, Object>> map = reportMapper.studentCountData();

        List<String> jobList = map.stream().map(x -> (String)x.get("clazz")).collect(Collectors.toList());
        List<Integer> dataList = map.stream().map(x -> ((Number)x.get("count")).intValue()).collect(Collectors.toList());

        return new ClazzOption(jobList, dataList);
    }

    /**
     * 学员学历信息统计
     */
    @Override
    public List<Map<String, Object>> studentDegreeData() {
        return reportMapper.studentDegreeData();
    }
}
