package com.reststyle.framework.service.security;

import cn.hutool.core.lang.Validator;
import com.reststyle.framework.common.enums.DelFlag;
import com.reststyle.framework.common.security.entity.SecurityDept;
import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.common.security.model.LoginUser;
import com.reststyle.framework.service.security.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 用户验证处理
 *
 * @author TheFei
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SecurityUser user = securityService.selectUserByUserName(username);
        if (Validator.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
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
        //SecurityDept dept = securityService.selectSecurityDeptByUserId(user.getUserId());
        //user.setDept(dept);

        List<SecurityRole> roles = securityService.selectSecurityRoleByUserId(user.getUserId());
        user.setRoles(roles);

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SecurityUser user)
    {
        Set<String> menuPermission = securityService.getMenuPermission(user);
        return new LoginUser();
    }
}
