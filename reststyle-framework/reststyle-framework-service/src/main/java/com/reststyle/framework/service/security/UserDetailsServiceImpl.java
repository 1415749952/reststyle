package com.reststyle.framework.service.security;

import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.service.security.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
    public SecurityUser loadUserByUsername(String username)
    {
        return securityService.selectUserByUserName(username);
    }

}
