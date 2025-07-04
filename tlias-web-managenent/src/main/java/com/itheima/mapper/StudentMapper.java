package com.itheima.mapper;

import com.itheima.pojo.EnquiryParam;
import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import static net.sf.jsqlparser.parser.feature.Feature.update;

@Mapper
public interface StudentMapper {
    List<Student> getPage(EnquiryParam student);

    /**
     * 添加学生
     */
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time) " +
            "values(#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{violationCount}, #{violationScore}, #{createTime}, #{updateTime})")
    void add(Student student);

    /**
     * 根据id查询学生信息
     */
    @Select("select id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time from student where id = #{id}")
    Student findById(Integer id);

    void alter(Student student);

    void deleteByIds(List<Integer> list);

    @Update("update student set violation_count = #{count}, violation_score = #{score} where id = #{id}")
    void updateViolation(Integer id, Integer count, Integer score);
}
