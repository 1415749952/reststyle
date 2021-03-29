package com.reststyle.framework.common.unite_response;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-25
 * @Time: 21:48
 */
@Data
public class PageParam
{
    /**
     * 页码，从1开始
     */
    private Integer pageNum;
    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 排序（哪一个字段）
     */
    private String orderBy;
    /**
     * ASC：升序（默认），DESC：降序。
     */
    private String sequence;
}