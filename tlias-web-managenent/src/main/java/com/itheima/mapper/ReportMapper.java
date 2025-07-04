package com.itheima.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    /**
     * 查询每个部门对应的人数
     * MapKey注解指定返回的map中的唯一标识是那个字段。
     */
    @MapKey("pos")
    List<Map<String, Object>> empJobData();

    @MapKey("name")
    List<Map<String, Object>> empDenderData();

    @MapKey("clazz")
    List<Map<String, Object>> studentCountData();

    @MapKey("name")
    List<Map<String, Object>> studentDegreeData();
}
