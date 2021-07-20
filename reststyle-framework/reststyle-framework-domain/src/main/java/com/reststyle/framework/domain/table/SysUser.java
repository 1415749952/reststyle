package com.reststyle.framework.domain.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:用户信息表
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 19:09
 */
@Data
@TableName(value = "sys_user")
public class SysUser implements Serializable
{
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    /**
     * 用户账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户类型（00系统用户）
     */
    @TableField(value = "user_type")
    private String userType;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号码
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 用户性别（1男 2女 9未知）
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 帐号是否启用
     */
    @TableField(value = "is_enabled")
    private Boolean isEnabled;

    /**
     * 凭证未过期
     */
    @TableField(value = "is_credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    /**
     * 帐户未锁定
     */
    @TableField(value = "is_account_non_locked")
    private Boolean isAccountNonLocked;

    /**
     * 帐户未过期
     */
    @TableField(value = "is_account_non_expired")
    private Boolean isAccountNonExpired;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}