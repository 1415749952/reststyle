package com.reststyle.framework.common.security.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 用户对象 sys_user
 *
 * @author TheFei
 */
@Data
public class SecurityUser implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

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
     * 角色对象
     */
    private List<SecurityRole> roles;

    /**
     * 权限列表
     */
    private Set<String> permissions;
}
