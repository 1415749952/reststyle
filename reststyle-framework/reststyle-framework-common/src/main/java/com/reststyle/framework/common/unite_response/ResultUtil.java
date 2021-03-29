package com.reststyle.framework.common.unite_response;

import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-05
 * @Time: 2:10
 */
public class ResultUtil
{
    /**
     * 成功操作，就用这个方法
     *
     * @return
     */
    public static RestResult success()
    {
        return new RestResult(true, HttpStatus.OK.value(), null, "操作成功！");
    }

    /**
     * 成功查询，返回一个对象使用这个方法
     *
     * @param data
     * @return
     */
    public static RestResult success(Object data)
    {
        return new RestResult(true, HttpStatus.OK.value(), data, "操作成功！");
    }

    /**
     * 成功查询，返回一个对象使用这个方法，
     * 自定义返回消息message
     *
     * @param data
     * @param message
     * @return
     */
    public static RestResult success(Object data, String message)
    {
        return new RestResult(true, HttpStatus.OK.value(), data, message);
    }

    /**
     * 成功查询，返回分页数据就用这个方法
     *
     * @return
     */
    public static RestResult success(PageInfo pageInfo)
    {
        PageBaseInfo pageBaseInfo = new PageBaseInfo(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
        return new RestResult(true, HttpStatus.OK.value(), pageBaseInfo, "操作成功！");
    }

    /**
     * 成功查询，返回分页数据就用这个方法，
     * 自定义返回消息message
     *
     * @return
     */
    public static RestResult success(PageInfo pageInfo, String message)
    {
        PageBaseInfo pageBaseInfo = new PageBaseInfo(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
        return new RestResult(true, HttpStatus.OK.value(), pageBaseInfo, message);
    }


    /**
     * 失败后，返回有错误数据
     *
     * @return
     */
    public static RestResult error()
    {
        return new RestResult(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "发生错误了！");
    }

    /**
     * 失败后，返回有错误数据
     *
     * @return
     */
    public static RestResult error(String message)
    {
        return new RestResult(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, message);
    }


    /**
     * 失败后，返回有错误数据
     *
     * @return
     */
    public static RestResult error(Object data)
    {
        return new RestResult(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), data, "发生错误了！");
    }

    /**
     * 失败后，返回有错误数据
     *
     * @return
     */
    public static RestResult error(Object data, String message)
    {
        return new RestResult(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), data, message);
    }


    /**
     * 失败后，返回没有错误数据
     *
     * @param httpStatus
     * @return
     */
    public static RestResult error(HttpStatus httpStatus)
    {
        return new RestResult(false, httpStatus.value(), null, "发生错误了！");
    }
    /**
     * 失败后，返回没有错误数据
     *
     * @param httpStatus
     * @return
     */
    public static RestResult error(HttpStatus httpStatus, String message)
    {
        return new RestResult(false, httpStatus.value(), null, message);
    }








    /**
     * 失败后，返回没有错误数据
     *
     * @param httpStatus
     * @return
     */
    public static RestResult error(HttpStatus httpStatus, Object data)
    {
        return new RestResult(false, httpStatus.value(), data, "发生错误了！");
    }

    /**
     * 失败后，返回有错误数据
     *
     * @param httpStatus
     * @return
     */
    public static RestResult error(HttpStatus httpStatus, Object data, String message)
    {
        return new RestResult(false, httpStatus.value(), data, message);
    }


}
