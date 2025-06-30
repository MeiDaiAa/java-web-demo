package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 工作经历
 */
@Data
public class EmpExpr {
    private Integer id; //ID
    private Integer empId; //员工ID
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //结束时间
    private String company; //公司名称
    private String job; //职位
}
