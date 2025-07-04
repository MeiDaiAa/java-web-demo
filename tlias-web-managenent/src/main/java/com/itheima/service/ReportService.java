package com.itheima.service;

import com.itheima.pojo.ClazzOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Map<String, Object>> empJobData();

    List<Map<String, Object>> empGenderData();

    ClazzOption studentCountData();

    List<Map<String, Object>> studentDegreeData();
}
