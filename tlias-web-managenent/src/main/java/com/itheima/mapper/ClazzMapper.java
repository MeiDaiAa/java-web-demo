package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.EnquiryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> findPage(EnquiryParam enquiryParam);

    //删除班级
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    //添加班级
    @Update("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) values (#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void add(Clazz clazz);

    Clazz findById(Integer id);

    void alter(Clazz clazz);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz")
    List<Clazz> list();
}
