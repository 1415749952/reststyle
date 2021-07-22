package com.reststyle.framework.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonView;
import com.reststyle.framework.common.unite_response.GeneralViews;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 返回页面数据的用户信息实体类，用来展示的。不用数据库实体类的原因：不想将其
 * vo就是我们在web的controller层返回的Object，
 * 在接口中这个VO都会被转成Json对象输出，view object。
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-22
 * @Time: 10:10
 */
@Data
public class GenTableVo implements Serializable
{
    /**
     * 不在列表中展示createTime和updateTime，返回列表JSON中就没有createTime和updateTime
     */
    public interface DBTableListView extends GeneralViews.RestView
    {
    }

    public interface GenTableView extends DBTableListView
    {
    }


    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    @JsonView(GenTableView.class)
    private Long tableId;

    /**
     * 表名称
     */
    @JsonView(DBTableListView.class)
    private String tableName;

    /**
     * 表描述
     */
    @JsonView(DBTableListView.class)
    private String tableComment;

    /**
     * 关联子表的表名
     */
    private String subTableName;

    /**
     * 子表关联的外键名
     */
    private String subTableFkName;

    /**
     * 实体类名称
     */
    @JsonView(GenTableView.class)
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    private String tplCategory;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 生成模块名
     */
    private String moduleName;

    /**
     * 生成业务名
     */
    private String businessName;

    /**
     * 生成功能名
     */
    private String functionName;

    /**
     * 生成功能作者
     */
    private String functionAuthor;

    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    private String genPath;

    /**
     * 其它生成选项
     */
    private String options;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonView(DBTableListView.class)
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonView(DBTableListView.class)
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

}
