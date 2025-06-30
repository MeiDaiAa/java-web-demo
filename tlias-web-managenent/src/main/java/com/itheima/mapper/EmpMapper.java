package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EnquiryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface EmpMapper {
//    /**
//     * 查询员工总记录数
//     */
//    @Select("select count(*) from emp")
//    public Long findTotal();
//
//    /**
//     * 分页查询查询员工列表
//     */
//    @Select("select e.*, d.name as deptName " +
//            "from emp e " +
//            "left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc " +
//            "limit #{start},#{pageSize}")
//    public List<Emp> findPage(Integer start, Integer pageSize);

    //pageHelper插件实现方法


    /**
     * 根据条件分页查询员工表
     */
    public List<Emp> findPage(EnquiryParam enquiryParam);

    /**
     * 添加员工
     */
    //设置主键返回
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(
            "insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
                    "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})"
    )
    void addEmp(Emp emp);

    /**
     * 添加员工对应的经历
     */
    void addEmpExpr(List<EmpExpr> exprList);

    /**
     * 通过id批量删除员工（包括员工经历）
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    void deleteExprByIds(List<Integer> empIds);

    Emp findById(Integer id);

    void alter(Emp emp);
}
