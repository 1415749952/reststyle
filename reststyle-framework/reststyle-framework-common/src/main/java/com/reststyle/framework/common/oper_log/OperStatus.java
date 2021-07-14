package com.reststyle.framework.common.oper_log;

import lombok.Getter;

/**
 * 操作状态
 * 
 * @author TheFei
 *
 */
@Getter
public enum OperStatus
{
    /**
     * 成功
     */
    SUCCESS("1","成功"),

    /**
     * 失败
     */
    FAIL("0","失败");

    private final String state;
    private final String value;

    OperStatus(String state, String value)
    {
        this.state = state;
        this.value = value;
    }
}
