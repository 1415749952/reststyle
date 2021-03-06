package com.reststyle.framework.common.security;

import com.reststyle.framework.common.exception.BusinessException;
import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 安全服务工具类
 * 
 * @author TheFei
 */
public class SecurityUtils
{
    /**
     * 获取用户账户
     **/
    public static String getUsername()
    {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new BusinessException("获取用户账户异常");
        }
    }

    /**
     * 获取用户
     **/
    public static SecurityUser getLoginUser()
    {
        try
        {
            return (SecurityUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new BusinessException("获取用户信息异常");
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     * 
     * @param user 用户ID
     * @return 结果
     */
    public static boolean isAdmin(SecurityUser user)
    {
        if (null == user)
        {
            return false;
        }
        if (CollectionUtils.isEmpty(user.getSecurityRoles()))
        {
            return false;
        }
        List<String> roleKeys = Optional.ofNullable(user.getSecurityRoles()).orElse(new ArrayList<>()).stream().map(SecurityRole::getRoleKey).collect(Collectors.toList());
        if (roleKeys.contains("admin"))
        {
            return true;
        }
        return false;
    }
}
