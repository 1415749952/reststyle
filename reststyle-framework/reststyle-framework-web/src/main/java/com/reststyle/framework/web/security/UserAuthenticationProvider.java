package com.reststyle.framework.web.security;

import com.reststyle.framework.common.constant.Constants;
import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.service.manager.AsyncManager;
import com.reststyle.framework.service.manager.factory.AsyncFactory;
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

import java.util.*;
import java.util.stream.Collectors;

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
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户："+username+" 不存在"));
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "密码不正确"));
            throw new BadCredentialsException("密码不正确");
        }
        if (user.getIsEnabled().equals(false))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号：" + username + " 已被停用"));
            throw new BadCredentialsException("对不起，您的账号：" + username + " 已被停用");
        }
        if (user.getIsAccountNonLocked().equals(false))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号：" + username + " 已被锁定"));
            throw new BadCredentialsException("对不起，您的账号：" + username + " 已被锁定");
        }
        if (user.getIsAccountNonExpired().equals(false))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号：" + username + " 已过期"));
            throw new BadCredentialsException("对不起，您的账号：" + username + " 已过期");
        }
        if (user.getIsCredentialsNonExpired().equals(false))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号：" + username + " 凭证已过期"));
            throw new BadCredentialsException("对不起，您的账号：" + username + " 凭证已过期");
        }

        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SecurityRole> securityRoles = securityService.selectSecurityRoleByUserId(user.getUserId());
        user.setSecurityRoles(securityRoles);

        List<String> permissions = securityService.selectPermissionByUserId(user);
        for (String permission : permissions)
        {
            authorities.add(new SimpleGrantedAuthority(permission));
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