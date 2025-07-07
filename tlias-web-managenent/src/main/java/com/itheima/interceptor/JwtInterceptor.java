package com.itheima.interceptor;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//配置拦截器
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");

        // 获取token
        String token = request.getHeader("token");
        log.info("获取到的token为：{}", token);

        //如果token为空，则返回401
        if(token == null || token.isEmpty()){
            log.error("token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //验证 token
        try{
            Claims claims = JwtUtils.parseJwt(token);
            //获取到员工id
            Integer employeeId = (Integer) claims.get("id");
            log.info("当前登录的员工id为：{}", employeeId);
            //将员工id保存到ThreadLocal中
            CurrentHolder.setCurrentId(employeeId);
        }catch (Exception e){;
            log.error("token非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        log.info("token验证通过, 放行");
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //释放资源
        CurrentHolder.remove();
        log.info("释放资源");
        log.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afteCompletion...");
    }
}
