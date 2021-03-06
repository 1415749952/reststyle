package com.reststyle.framework.service.security.service.impl;

import com.reststyle.framework.common.security.SecurityUtils;
import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.mapper.security.SecurityMapper;
import com.reststyle.framework.service.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 20:59
 */
@Service
public class SecurityServiceImpl implements SecurityService
{
    @Autowired
    private SecurityMapper securityMapper;

    @Override
    public SecurityUser selectUserByUserName(String username)
    {
        return securityMapper.selectSecurityUserByUserName(username);
    }

    @Override
    public List<SecurityRole> selectSecurityRoleByUserId(Long userId)
    {
        return securityMapper.selectSecurityRoleByUserId(userId);
    }



    @Override
    public List<String> selectPermissionByUserId(SecurityUser user)
    {

        List<String> menuList;
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(user))
        {
            menuList = securityMapper.selectAllMenu();
        }
        else
        {
            Set<Long> roleIds = Optional.ofNullable(user.getSecurityRoles()).orElse(new ArrayList<>()).stream().map(SecurityRole::getRoleId).collect(Collectors.toSet());
            menuList = securityMapper.selectMenuPermsByRoleIds(roleIds);
        }
        return menuList;
    }
}
