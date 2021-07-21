package com.reststyle.framework.domain.table;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 代码生成业务表
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-21
 * @Time: 15:37
 */
@Data
@TableName(value = "gen_table")
public class GenTable implements Serializable
{
    /**
     * 编号
     */
    @TableId(value = "table_id", type = IdType.ASSIGN_ID)
    private Long tableId;

    /**
     * 表名称
     */
    @TableField(value = "table_name")
    private String tableName;

    /**
     * 表描述
     */
    @TableField(value = "table_comment")
    private String tableComment;

    /**
     * 关联子表的表名
     */
    @TableField(value = "sub_table_name")
    private String subTableName;

    /**
     * 子表关联的外键名
     */
    @TableField(value = "sub_table_fk_name")
    private String subTableFkName;

    /**
     * 实体类名称
     */
    @TableField(value = "class_name")
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    @TableField(value = "tpl_category")
    private String tplCategory;

    /**
     * 生成包路径
     */
    @TableField(value = "package_name")
    private String packageName;

    /**
     * 生成模块名
     */
    @TableField(value = "module_name")
    private String moduleName;

    /**
     * 生成业务名
     */
    @TableField(value = "business_name")
    private String businessName;

    /**
     * 生成功能名
     */
    @TableField(value = "function_name")
    private String functionName;

    /**
     * 生成功能作者
     */
    @TableField(value = "function_author")
    private String functionAuthor;

    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    @TableField(value = "gen_type")
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    @TableField(value = "gen_path")
    private String genPath;

    /**
     * 其它生成选项
     */
    @TableField(value = "options")
    private String options;

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
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}