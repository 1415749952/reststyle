package com.reststyle.framework.service.security.service;

import com.reststyle.framework.common.security.entity.SecurityDept;
import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;

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
public interface SecurityService
{
    SecurityUser selectUserByUserName(String username);

    SecurityDept selectSecurityDeptByUserId(Long userId);

    List<SecurityRole> selectSecurityRoleByUserId(Long userId);

}
