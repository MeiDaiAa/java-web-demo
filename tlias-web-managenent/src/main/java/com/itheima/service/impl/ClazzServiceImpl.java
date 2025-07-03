package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.EnquiryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzMapper clazzMapper;

    /**
     * 班级列表数据的条件分页查询
     */
    @Override
    public PageResult<Clazz> findPage(EnquiryParam enquiryParam) {
        //使用pageHelper实现分页
        PageHelper.startPage(enquiryParam.getPage(),enquiryParam.getPageSize());
        List<Clazz> clazzs = clazzMapper.findPage(enquiryParam);
        Page<Clazz> p = (Page<Clazz>) clazzs;

        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    /**
     * 根据id删除班级
     */
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    /**
     * 添加班级
     */
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.add(clazz);
    }

    /**
     * 根据id查询班级信息
     */
    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);
    }

    /**
     * 修改班级信息
     */
    @Override
    public void alter(Clazz clazz) {
        //修改更新时间
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.alter(clazz);
    }
}
