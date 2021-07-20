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
 * Description:系统访问记录
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-29
 * @Time: 9:25
 */
@Data
@TableName(value = "sys_logininfor")
public class SysLogininfor implements Serializable
{
    /**
     * 访问ID
     */
    @TableId(value = "info_id", type = IdType.ASSIGN_ID)
    private Long infoId;

    /**
     * 用户账号
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录IP地址
     */
    @TableField(value = "ipaddr")
    private String ipaddr;

    /**
     * 登录地点
     */
    @TableField(value = "login_location")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    /**
     * 登录状态（0成功 1失败）
     */
    @TableField(value = "state")
    private String state;

    /**
     * 提示消息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 访问时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    private static final long serialVersionUID = 1L;
}