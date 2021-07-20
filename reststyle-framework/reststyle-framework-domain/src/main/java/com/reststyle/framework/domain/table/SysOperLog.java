package com.reststyle.framework.domain.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 *  Created with IntelliJ IDEA.
 *  Description:操作日志记录
 *  @version 1.0
 *  @author: TheFei
 *  @Date: 2021-03-25
 *  @Time: 22:04
 * 
 */
@Data
@TableName(value = "sys_oper_log")
public class SysOperLog
{
    /**
     * 日志主键
     */
    @TableId(value = "oper_id", type = IdType.INPUT)
    private Long operId;

    /**
     * 业务类型(其他other,删除delete,查询select,修改update,新增INSERT("insert,生成代码generate,下载download,导出export,授权grant,导入import,强退force,清空数据clean
     */
    @TableField(value = "business_type")
    private Integer businessType;

    /**
     * 方法名称
     */
    @TableField(value = "method")
    private String method;

    /**
     * 方法描述(这个方法是干什么的)
     */
    @TableField(value = "method_detail")
    private String methodDetail;

    /**
     * 请求URL
     */
    @TableField(value = "request_url")
    private String requestUrl;

    /**
     * 请求方式(post,get,put,delete)
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 请求参数
     */
    @TableField(value = "request_param")
    private String requestParam;

    /**
     * 返回参数
     */
    @TableField(value = "respose_result")
    private String resposeResult;

    /**
     * 操作状态（0正常 1异常）
     */
    @TableField(value = "state")
    private String state;

    /**
     * 被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    @TableField(value = "oper_unit")
    private String operUnit;

    /**
     * 操作客户端
     */
    @TableField(value = "oper_client")
    private String operClient;

    /**
     * 操作人员
     */
    @TableField(value = "oper_name")
    private String operName;

    /**
     * 主机地址
     */
    @TableField(value = "oper_ip")
    private String operIp;

    /**
     * 操作地点
     */
    @TableField(value = "oper_address")
    private String operAddress;

    /**
     * 错误消息
     */
    @TableField(value = "error_msg")
    private String errorMsg;

    /**
     * 操作时间
     */
    @TableField(value = "oper_time")
    private Date operTime;
}