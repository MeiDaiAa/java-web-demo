package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.EnquiryParam;
import com.itheima.pojo.PageResult;

public interface ClazzService {

    PageResult<Clazz> findPage(EnquiryParam enquiryParam);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz findById(Integer id);

    void alter(Clazz clazz);
}
