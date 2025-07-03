package com.itheima.service.impl;

import com.itheima.mapper.ReportMapper;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
}
