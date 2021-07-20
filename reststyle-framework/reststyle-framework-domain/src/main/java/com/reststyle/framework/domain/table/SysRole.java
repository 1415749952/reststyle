package com.reststyle.framework.domain.table;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:角色信息表
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 19:09
 */
@Data
@TableName(value = "sys_role")
public class SysRole implements Serializable
{
    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @TableField(value = "role_key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @TableField(value = "role_sort")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @TableField(value = "data_scope")
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示
     */
    @TableField(value = "menu_check_strictly")
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    @TableField(value = "dept_check_strictly")
    private Boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "state")
    private String state;

    /**
     * 创建者
     * 注意！这里需要标记为填充字段        fill = FieldFill.INSERT
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     * 注意！这里需要标记为填充字段        fill = FieldFill.INSERT
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标志（0代表存在 2代表删除）
     * 注意！这里需要标记为填充字段        fill = FieldFill.INSERT
     */
    @TableField(value = "del_flag",fill = FieldFill.INSERT)
    private String delFlag;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}