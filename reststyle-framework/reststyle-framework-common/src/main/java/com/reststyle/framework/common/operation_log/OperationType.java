package com.reststyle.framework.common.operation_log;

/**
 * Created with IntelliJ IDEA.
 * Description:记录操作日志的操作类型枚举类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:23
 */
public enum OperationType
{
    /**
     * 其他
     */
    OTHER("other"),
    /**
     * 删除
     */
    DELETE("delete"),
    /**
     * 查询
     */
    SELECT("select"),
    /**
     * 修改
     */
    UPDATE("update"),
    /**
     * 新增
     */
    INSERT("insert"),
    /**
     * 生成代码
     */
    GENERATE("generate"),
    /**
     * 下载
     */
    DOWNLOAD("download"),
    /**
     * 导出
     */
    EXPORT("export"),
    /**
     * 授权
     */
    GRANT("grant"),
    /**
     * 导入
     */
    IMPORT("import"),
    /**
     * 强退
     */
    FORCE("force"),
    /**
     * 清空数据
     */
    CLEAN("clean");

    private String value;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    OperationType(String s)
    {
        this.value = s;
    }
}
