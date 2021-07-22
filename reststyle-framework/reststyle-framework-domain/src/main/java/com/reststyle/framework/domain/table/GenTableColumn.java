package com.reststyle.framework.domain.table;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 代码生成业务表字段
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-21
 * @Time: 15:39
 */
@Data
@TableName(value = "gen_table_column")
public class GenTableColumn implements Serializable
{
    /**
     * 编号
     */
    @TableId(value = "column_id", type = IdType.ASSIGN_ID)
    private Long columnId;

    /**
     * 归属表编号
     */
    @TableField(value = "table_id")
    private Long tableId;

    /**
     * 列名称
     */
    @TableField(value = "column_name")
    private String columnName;

    /**
     * 列描述
     */
    @TableField(value = "column_comment")
    private String columnComment;

    /**
     * 列类型
     */
    @TableField(value = "column_type")
    private String columnType;

    /**
     * JAVA类型
     */
    @TableField(value = "java_type")
    private String javaType;

    /**
     * JAVA字段名
     */
    @TableField(value = "java_field")
    private String javaField;

    /**
     * 是否主键（1是）
     */
    @TableField(value = "is_pk")
    private String isPk;

    /**
     * 是否自增（1是）
     */
    @TableField(value = "is_increment")
    private String isIncrement;

    /**
     * 是否必填（1是）
     */
    @TableField(value = "is_required")
    private String isRequired;

    /**
     * 是否为插入字段（1是）
     */
    @TableField(value = "is_insert")
    private String isInsert;

    /**
     * 是否编辑字段（1是）
     */
    @TableField(value = "is_edit")
    private String isEdit;

    /**
     * 是否列表字段（1是）
     */
    @TableField(value = "is_list")
    private String isList;

    /**
     * 是否查询字段（1是）
     */
    @TableField(value = "is_query")
    private String isQuery;

    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    @TableField(value = "query_type")
    private String queryType;

    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @TableField(value = "html_type")
    private String htmlType;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type")
    private String dictType;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

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

    private static final long serialVersionUID = 1L;


    public boolean isPk()
    {
        return isPk(this.isPk);
    }

    public boolean isPk(String isPk)
    {
        return isPk != null && "1".equals(isPk);
    }
}