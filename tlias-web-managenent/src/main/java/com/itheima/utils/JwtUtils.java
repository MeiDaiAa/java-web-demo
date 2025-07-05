package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {
    //设置密匙
    private static final String key = "aXRjYXN0";
    //设置过期时间
    private static final long expire = 3600 * 10 * 1000L;

    /**
     * 生成JWT令牌
     */
    public static String genJwt(Map<String, Object> map){
        String compact = Jwts.builder().signWith(SignatureAlgorithm.HS256, key)//设置密匙与签名算法
                .addClaims(map)//设置数据
                .setExpiration(new Date(System.currentTimeMillis() + expire))//设置过期时间为10小时
                .compact();//compact()方法将JWT转为字符串

        log.info("生成JWT成功：{}", compact);

        return compact;
    }

    /**
     * 解析JWT令牌
     */
    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(key)//设置密匙
                .parseClaimsJws(jwt)//解析JWT令牌
                .getBody();//获取数据
    }
}
