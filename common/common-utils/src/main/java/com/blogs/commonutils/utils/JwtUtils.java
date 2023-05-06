package com.blogs.commonutils.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）,默认：{"alg": "HS512","typ": "JWT"}
 * payload的格式 设置：（用户信息、创建时间、生成时间）
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * @author 21380
 */
public class JwtUtils {

    /**
     * 私钥
     */
    private final static String secret = "secretKey";

    /**
     * 过期时间（单位秒）/ 2小时
      */
    private final static Long EXPIRE_TIME = 7200L;

    /**
     * 创建TOKEN
     * @return
     */
    public static String createToken(Map<String, Object> map){
        Long id = (Long) map.get("id");
        String username = (String) map.get("username");
        String avatar = (String) map.get("avatar");
        String email = (String) map.get("email");
        return  JWT.create()
                .withClaim("id", id)
                .withClaim("username", username)
                .withClaim("avatar", avatar)
                .withClaim("email", email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC512(secret));
    }

    /**
     * 验证token
     * @param token
     */
    public static Map<String, Claim> validateToken(String token){
        try {
            return JWT.require(Algorithm.HMAC512(secret)).build()
                    .verify(token).getClaims();
        } catch (TokenExpiredException e){
            return null;
        }
    }

    /**
     * 检查token是否需要更新
     * @param token
     * @return
     */
    public static boolean isNeedUpdate(String token){
        //获取token过期时间
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC512(secret))
                    .build()
                    .verify(token)
                    .getExpiresAt();
        } catch (TokenExpiredException e){
            return true;
        }
        //如果剩余过期时间少于过期时常的一般时 需要更新
        return (expiresAt.getTime()-System.currentTimeMillis()) < (EXPIRE_TIME >>1);
    }
}
