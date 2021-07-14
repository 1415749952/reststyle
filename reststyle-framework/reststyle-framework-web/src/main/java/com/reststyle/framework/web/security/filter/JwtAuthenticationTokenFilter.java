package com.reststyle.framework.web.security.filter;

import com.reststyle.framework.common.constant.Constants;
import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.utils.spring.SpringUtils;
import com.reststyle.framework.service.manager.AsyncManager;
import com.reststyle.framework.service.manager.factory.AsyncFactory;
import com.reststyle.framework.service.security.UserDetailsServiceImpl;
import com.reststyle.framework.service.security.service.impl.SecurityServiceImpl;
import com.reststyle.framework.web.config.JWTConfig;
import com.reststyle.framework.common.security.entity.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

                // 查询用户是否存在
                SecurityUser securityUser = SpringUtils.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);

                if (null == securityUser)
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户："+username+" 不存在"));
                    throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
                }

                if (securityUser.getIsEnabled().equals(false))
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号：" + username + " 已被停用"));
                    throw new BadCredentialsException("对不起，您的账号：" + username + " 已被停用");
                }
                if (securityUser.getIsAccountNonLocked().equals(false))
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号：" + username + " 已被锁定"));
                    throw new BadCredentialsException("对不起，您的账号：" + username + " 已被锁定");
                }
                if (securityUser.getIsAccountNonExpired().equals(false))
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号：" + username + " 已过期"));
                    throw new BadCredentialsException("对不起，您的账号：" + username + " 已过期");
                }
                if (securityUser.getIsCredentialsNonExpired().equals(false))
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号：" + username + " 凭证已过期"));
                    throw new BadCredentialsException("对不起，您的账号：" + username + " 凭证已过期");
                }

                // 角色集合
                Set<GrantedAuthority> authorities = new HashSet<>();
                // 查询用户角色
                List<SecurityRole> securityRoles = SpringUtils.getBean(SecurityServiceImpl.class).selectSecurityRoleByUserId(securityUser.getUserId());
                securityUser.setSecurityRoles(securityRoles);

                List<String> permissions = SpringUtils.getBean(SecurityServiceImpl.class).selectPermissionByUserId(securityUser);
                for (String permission : permissions)
                {
                    authorities.add(new SimpleGrantedAuthority(permission));
                }
                securityUser.setAuthorities(authorities);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, securityUser.getUserId(), authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
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
