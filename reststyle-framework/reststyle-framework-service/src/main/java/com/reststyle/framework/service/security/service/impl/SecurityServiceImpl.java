package com.reststyle.framework.service.security.service.impl;

import com.reststyle.framework.common.security.entity.SecurityDept;
import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.mapper.security.SecurityMapper;
import com.reststyle.framework.service.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public SecurityDept selectSecurityDeptByUserId(Long userId)
    {
        return securityMapper.selectSecurityDeptByUserId(userId);
    }

    @Override
    public List<SecurityRole> selectSecurityRoleByUserId(Long userId)
    {
        return securityMapper.selectSecurityRoleByUserId(userId);
    }

}
