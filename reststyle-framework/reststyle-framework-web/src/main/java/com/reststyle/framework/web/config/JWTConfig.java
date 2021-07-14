package com.reststyle.framework.web.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:JWT配置类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 16:14
 */
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig
{
    /**
     * 密钥KEY
     */
    public static String secret;
    /**
     * accessTokenKey
     */
    public static String accessTokenHeader;

    /**
     * refreshTokenKey
     */
    public static String refreshTokenHeader;

    /**
     * accessToken前缀字符
     */
    public static String accessTokenPrefix;
    /**
     * refreshToken前缀字符
     */
    public static String refreshTokenPrefix;
    /**
     * accessToken过期时间
     */
    public static Integer accessTokenExpiration;

    /**
     * refreshToken过期时间
     */
    public static Integer refreshTokenExpiration;

    /**
     * 不需要认证的接口
     */
    public static String antMatchers;


    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public void setAccessTokenHeader(String accessTokenHeader)
    {
        this.accessTokenHeader = accessTokenHeader;
    }

    public void setRefreshTokenHeader(String refreshTokenHeader)
    {
        this.refreshTokenHeader = refreshTokenHeader;
    }

    public void setRefreshTokenPrefix(String refreshTokenPrefix)
    {
        this.refreshTokenPrefix= refreshTokenPrefix;
    }

    public void setAccessTokenPrefix(String accessTokenPrefix)
    {
        this.accessTokenPrefix= accessTokenPrefix;
    }

    public void setAccessTokenExpiration(Integer accessTokenExpiration)
    {
        this.accessTokenExpiration = accessTokenExpiration * 1000;
    }

    public void setRefreshTokenExpiration(Integer refreshTokenExpiration)
    {
        this.refreshTokenExpiration = refreshTokenExpiration * 1000;
    }


    public void setAntMatchers(String antMatchers)
    {
        this.antMatchers = antMatchers;
    }
}