package com.reststyle.framework.common.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 21:04
 */
@Getter
public enum DelFlag
{
    /**
     * 已删除
     */
    LOGIC_DELETE_VALUE("2"),
    /**
     * 未删除
     */
    LOGIC_NOT_DELETE_VALUE("0");

    private String value;

    DelFlag(String value)
    {
        this.value = value;
    }
}
