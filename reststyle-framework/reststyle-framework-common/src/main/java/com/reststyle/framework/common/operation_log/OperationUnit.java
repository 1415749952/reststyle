package com.reststyle.framework.common.operation_log;

/**
 * Created with IntelliJ IDEA.
 * Description: 记录日志被操作的单元
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:24
 */
public enum OperationUnit
{

    /**
     * 被操作的单元
     */
    UNKNOWN("unknown"),
    USER("user"),
    EMPLOYEE("employee"),
    REDIS("redis");

    private String value;

    OperationUnit(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
