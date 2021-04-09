package com.reststyle.framework.common.security.model;

import com.reststyle.framework.common.security.entity.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录用户身份权限
 *
 * @author TheFei
 */
public class LoginUser implements UserDetails
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     */
    private SecurityUser user;

    public SecurityUser getUser()
    {
        return user;
    }

    public void setUser(SecurityUser user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return user.getPermissions();
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return user.getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return user.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return user.getIsCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled()
    {
        return user.getIsEnabled();
    }
}
