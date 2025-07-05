package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")//拦截所有请求
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取到http请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //放行掉/login请求
        if(request.getRequestURI().contains("login")){
            log.info("放行登录请求");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        // 获取token
        String token = request.getHeader("token");
        log.info("获取到的token为：{}", token);

        //如果token为空，则返回401
        if(token == null || token.isEmpty()){
            log.error("token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //验证 token
        try{
            JwtUtils.parseJwt(token);
        }catch (Exception e){;
            log.error("token非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.info("token验证通过, 放行");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("销毁");
        Filter.super.destroy();
    }
}
