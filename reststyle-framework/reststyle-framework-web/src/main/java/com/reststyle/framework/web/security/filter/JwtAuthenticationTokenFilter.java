package com.reststyle.framework.web.security.filter;

import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.common.utils.spring.SpringUtils;
import com.reststyle.framework.service.security.UserDetailsServiceImpl;
import com.reststyle.framework.service.security.service.impl.SecurityServiceImpl;
import com.reststyle.framework.web.config.JWTConfig;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                    log.info("Token验证时："+"登录用户：" + username + " 不存在");
                    throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
                }

                String password = claims.get("password").toString();
                // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
                if (!password.equals(securityUser.getPassword()))
                {
                    log.info("Token验证时："+"更改密码后token失效");
                    throw new BadCredentialsException("更改密码后token失效");
                }

                if (securityUser.getIsEnabled().equals(false))
                {
                    log.info("Token验证时："+"对不起，您的账号：" + username + " 已被停用");
                    throw new BadCredentialsException("对不起，您的账号：" + username + " 已被停用");
                }
                if (securityUser.getIsAccountNonLocked().equals(false))
                {
                    log.info("Token验证时："+"对不起，您的账号：" + username + " 已被锁定");
                    throw new BadCredentialsException("对不起，您的账号：" + username + " 已被锁定");
                }
                if (securityUser.getIsAccountNonExpired().equals(false))
                {
                    log.info("Token验证时："+"对不起，您的账号：" + username + " 已过期");
                    throw new BadCredentialsException("对不起，您的账号：" + username + " 已过期");
                }
                if (securityUser.getIsCredentialsNonExpired().equals(false))
                {
                    log.info("Token验证时："+"对不起，您的账号：" + username + " 凭证已过期");
                    throw new BadCredentialsException("对不起，您的账号：" + username + " 凭证已过期");
                }

                // 角色集合
                Set<GrantedAuthority> authorities = new HashSet<>();
                // 查询用户角色
                List<SecurityRole> securityRoles = SpringUtils.getBean(SecurityServiceImpl.class).selectSecurityRoleByUserId(securityUser);
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
