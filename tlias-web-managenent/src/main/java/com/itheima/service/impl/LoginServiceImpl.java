package com.itheima.service.impl;

import com.itheima.exception.LoginException;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.service.LoginService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    EmpMapper empMapper;
    /**
     * 员工登录Tlias智能学习辅助系统，登录完毕后，系统下发JWT令牌。
     */
    @Override
    public LoginInfo login(Emp emp) {
        String username = emp.getUsername();
        String password = emp.getPassword();

        Emp retEmp = empMapper.findByUsernameAndPassword(username, password);

        //如果为空抛出异常
        if(retEmp == null){
            throw new LoginException("用户或密码不正确");
        }

        //生成JWT令牌
        Map<String, Object> map = new HashMap<>();
        map.put("id", retEmp.getId());
        map.put("username", retEmp.getUsername());

        String token = JwtUtils.genJwt( map);

        //封装返回对象
        return new LoginInfo(retEmp.getId(), retEmp.getUsername(), retEmp.getName(), token);
    }
}
