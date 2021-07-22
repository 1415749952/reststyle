package com.reststyle.framework.domain.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 查询数据时的父类，包含分页大小（默认为10），当前页数（默认为1），排序列，排序的方向等字段
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-22
 * @Time: 10:33
 */
@Data
public class BaseQueryBo implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 当前页数
     */
    private Integer pageNum = Integer.valueOf("1");
    /**
     * 分页大小
     */
    private Integer pageSize = Integer.valueOf("10");
    /**
     * 排序列
     */
    private String orderByColumn;
    /**
     * 排序的方向desc或者asc
     */
    private String isAsc;
    /**
     * 预留查询字段
     */
    private Map<String, Object> params = new HashMap<>();
}
