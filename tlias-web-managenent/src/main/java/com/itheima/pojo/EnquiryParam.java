package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EnquiryParam {
    private String name;//姓名
    private Integer gender;//性别 , 1 男 , 2 女
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;//范围匹配的开始时间(入职日期)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;//范围匹配的结束时间(入职日期)
    private Integer page = 1;//分页查询的页码，如果未指定，默认为1
    private Integer pageSize = 10;//分页查询的每页记录数，如果未指定，默认为10
}
