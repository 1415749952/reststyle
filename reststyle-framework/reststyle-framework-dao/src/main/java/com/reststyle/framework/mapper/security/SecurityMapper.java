package com.reststyle.framework.mapper.security;

import com.reststyle.framework.common.security.entity.SecurityRole;
import com.reststyle.framework.common.security.entity.SecurityUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    List<SecurityRole> selectSecurityRoleByUserId(Long userId);

    List<String> selectMenuPermsByRoleIds(@Param("roleIds") Set<Long> roleIds);

    List<String> selectAllMenu();
}
