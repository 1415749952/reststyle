package com.reststyle.framework.common.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:  SpringSecurity用户的实体。注意:这里必须要实现UserDetails接口
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:22
 */
@Data
public class SecurityUser implements Serializable, UserDetails
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;


    /**
     * 帐号是否启用
     */
    private Boolean isEnabled;

    /**
     * 凭证未过期
     */
    private Boolean isCredentialsNonExpired;

    /**
     * 帐户未锁定
     */
    private Boolean isAccountNonLocked;

    /**
     * 帐户未过期
     */
    private Boolean isAccountNonExpired;

    /**
     * 用户角色
     */
    private Collection<GrantedAuthority> authorities;

    /**
     * 用户角色
     */
    private List<SecurityRole> securityRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled()
    {
        return this.isEnabled;
    }
}
