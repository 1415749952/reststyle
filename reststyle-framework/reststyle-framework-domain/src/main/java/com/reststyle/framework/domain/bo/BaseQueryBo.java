package com.reststyle.framework.domain.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
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
