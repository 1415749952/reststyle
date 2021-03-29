package com.reststyle.framework.mapper.security;

import com.reststyle.framework.common.security.entity.SecurityDept;
import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 21:34
 */
@Mapper
@Repository
public interface SecurityMapper
{
    SecurityUser selectSecurityUserByUserName(String username);

    SecurityDept selectSecurityDeptByUserId(Long userId);

    List<SecurityRole> selectSecurityRoleByUserId(Long userId);

}
