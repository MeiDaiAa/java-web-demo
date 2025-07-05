package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 员工登录Tlias智能学习辅助系统，登录完毕后，系统下发JWT令牌。
     */
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("用户登录：{}, {}", emp.getUsername(), emp.getPassword());

        LoginInfo loginInfo = loginService.login(emp);

        return Result.success(loginInfo);
    }
}
