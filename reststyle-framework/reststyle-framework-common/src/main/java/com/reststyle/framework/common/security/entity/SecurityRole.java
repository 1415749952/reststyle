package com.reststyle.framework.common.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色表 sys_role
 *
 * @author TheFei
 */
@Data
public class SecurityRole implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

}
