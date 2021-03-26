package com.reststyle.framework.common.operation_log;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:记录操作日志的操作类型枚举类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:23
 */
@Getter
public enum BusinessType
{

    /**
     * 新增
     */
    INSERT(1,"新增"),
    /**
     * 删除
     */
    DELETE(2,"删除"),
    /**
     * 修改
     */
    UPDATE(3,"修改"),
    /**
     * 查询
     */
    SELECT(4,"查询"),
    /**
     * 导入
     */
    IMPORT(5,"导入"),
    /**
     * 导出
     */
    EXPORT(6,"导出"),
    /**
     * 下载
     */
    DOWNLOAD(7,"下载"),
    /**
     * 授权
     */
    GRANT(8,"授权"),
    /**
     * 强退
     */
    FORCE(9,"强退"),
    /**
     * 清空数据
     */
    CLEAN(10,"清空数据"),
    /**
     * 生成代码
     */
    GENERATE(11,"生成代码"),
    /**
     * 其他
     */
    OTHER(99,"其他");

    private final int key;
    private final String value;

    BusinessType(int key, String value)
    {
        this.key = key;
        this.value = value;
    }
}
