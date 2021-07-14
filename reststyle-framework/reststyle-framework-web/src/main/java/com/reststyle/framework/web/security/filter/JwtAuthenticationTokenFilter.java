package com.reststyle.framework.web.security.filter;

import com.reststyle.framework.common.utils.json.JacksonUtils;
import com.reststyle.framework.web.config.JWTConfig;
import com.reststyle.framework.common.security.entity.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:JWT接口请求校验拦截器，请求接口时会进入这里验证Token是否合法和过期
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:22
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter
{
    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager)
    {
        super(authenticationManager);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(JWTConfig.accessTokenHeader);
        if (null != tokenHeader && tokenHeader.startsWith(JWTConfig.accessTokenPrefix))
        {
            try
            {
                // 截取JWT前缀
                String token = tokenHeader.replace(JWTConfig.accessTokenPrefix, "");
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JWTConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名
                String username = claims.getSubject();
                String userId = claims.getId();
                if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId))
                {
                    // 获取角色
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String authority = claims.get("authorities").toString();
                    if (!StringUtils.isEmpty(authority))
                    {
                        List<Map<String, String>> authorityMap = (List<Map<String, String>>)JacksonUtils.convertJson2List(authority);
                        for (Map<String, String> role : authorityMap)
                        {
                            if (!StringUtils.isEmpty(role))
                            {
                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                            }
                        }
                    }
                    //组装参数
                    SecurityUser SecurityUser = new SecurityUser();
                    SecurityUser.setUsername(claims.getSubject());
                    SecurityUser.setUserId(Long.parseLong(claims.getId()));
                    SecurityUser.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(SecurityUser, userId, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            catch (ExpiredJwtException e)
            {
                log.info("Token过期");
            }
            catch (Exception e)
            {
                log.info("Token无效");
            }
        }
        filterChain.doFilter(request, response);
    }
}
