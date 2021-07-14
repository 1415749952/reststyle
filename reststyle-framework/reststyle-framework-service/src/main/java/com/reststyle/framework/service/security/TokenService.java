package com.reststyle.framework.service.security;

import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenService
{

    /**
     * 生成AccessToken
     *
     * @param securityUser 用户安全实体
     * @param expiration   token过期时间
     * @param secret       token密钥KEY
     * @return
     */
    public String createAccessToken(SecurityUser securityUser, Integer expiration, String secret)
    {
        // 登陆成功生成JWT
        return Jwts.builder()
                // 放入用户名和用户ID
                .setId(securityUser.getUserId() + "")
                // 主题
                .setSubject(securityUser.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("restStyle")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JacksonUtils.object2Json(securityUser.getAuthorities()))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * 生成RefreshToken
     *
     * @param securityUser 用户安全实体
     * @param expiration   token过期时间
     * @param secret       token密钥KEY
     * @return
     */
    public String createRefreshToken(SecurityUser securityUser, Integer expiration, String secret)
    {
        // 登陆成功生成JWT
        return Jwts.builder()
                // 放入用户名和用户ID
                .setId(securityUser.getUserId() + "")
                // 主题
                .setSubject(securityUser.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("restStyle")
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


}
