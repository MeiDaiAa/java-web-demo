package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门信息
     */
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    public List<Dept> findAll();


    /**
     * 根据id删除部门
     */
    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    /**
     * 添加部门
     */
    @Update("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    public void add(Dept dept);

    /**
     * 根据Id查找部门
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    public Dept findById(Integer id);

    /**
     * 修改部门数据
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    public void alter(Dept dept);
}
