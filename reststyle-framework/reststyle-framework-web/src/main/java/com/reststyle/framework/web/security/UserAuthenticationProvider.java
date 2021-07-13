package com.reststyle.framework.web.security;

import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.service.business.SysUserService;
import com.reststyle.framework.service.security.UserDetailsServiceImpl;
import com.reststyle.framework.service.security.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:自定义登录验证
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:22
 */
@Slf4j
@Component
public class UserAuthenticationProvider implements AuthenticationProvider
{
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private SecurityService securityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        // 获取表单输入中返回的用户名
        String username = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        SecurityUser user = userDetailsServiceImpl.loadUserByUsername(username);


        if (null == user)
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword()))
        {
            throw new BadCredentialsException("密码不正确");
        }
        if (user.getIsEnabled().equals(false))
        {
            throw new BadCredentialsException("对不起，您的账号：" + username + " 已被停用");
        }
        if (user.getIsAccountNonLocked().equals(false))
        {
            throw new BadCredentialsException("对不起，您的账号：" + username + " 已被锁定");
        }
        if (user.getIsAccountNonExpired().equals(false))
        {
            throw new BadCredentialsException("对不起，您的账号：" + username + " 已过期");
        }
        if (user.getIsCredentialsNonExpired().equals(false))
        {
            throw new BadCredentialsException("对不起，您的账号：" + username + " 凭证已过期");
        }

        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SecurityRole> securityRoles = securityService.selectSecurityRoleByUserId(user.getUserId());

        user.setSecurityRoles(securityRoles);
        for (SecurityRole sysRoleEntity : securityRoles)
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
        }
        user.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return true;
    }
}