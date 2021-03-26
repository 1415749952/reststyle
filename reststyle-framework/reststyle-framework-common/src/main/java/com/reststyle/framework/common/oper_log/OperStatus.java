package com.reststyle.framework.common.oper_log;

import lombok.Getter;

/**
 * 操作状态
 * 
 * @author ruoyi
 *
 */
@Getter
public enum OperStatus
{
    /**
     * 成功
     */
    SUCCESS(1,"成功"),

    /**
     * 失败
     */
    FAIL(0,"失败");

    private final int status;
    private final String value;

    OperStatus(int status, String value)
    {
        this.status = status;
        this.value = value;
    }
}
