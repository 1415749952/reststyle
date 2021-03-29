package com.reststyle.framework.common.security.entity;

import com.reststyle.framework.common.domain.BaseEntity;

/**
 * 字典类型表 sys_dict_type
 *
 * @author TheFei
 */
public class SysDictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    private Long dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;
}
