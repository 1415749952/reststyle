package com.reststyle.framework.common.unite_response;


import com.fasterxml.jackson.annotation.JsonView;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Description:封装返回类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-04
 * @Time: 22:48
 */
public class RestResult
{
    /**
     * 请求是否成功（这个字段不需要，可以不加）
     */
    @JsonView(GeneralViews.RestView.class)
    private boolean success;
    /**
     * 成功或者失败的code错误码
     */
    @JsonView(GeneralViews.RestView.class)
    private Integer code;
    /**
     * 成功时返回的数据，失败时返回具体的异常信息
     */
    @JsonView(GeneralViews.RestView.class)
    private Object data;
    /**
     * 请求失败返回的提示信息，给前端进行页面展示的信息
     */
    @JsonView(GeneralViews.RestView.class)
    private String message;
    /**
     * 服务器当前时间（添加该字段的原因是便于查找定位请求时间，因为实际开发过程中服务器时间可能跟本地时间不一致，加上这个时间戳便于日后定位）
     */
    @JsonView(GeneralViews.RestView.class)
    private Timestamp currentTime;

    public RestResult()
    {
    }

    @Override
    public String toString()
    {
        return "RestResult{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", data=" + data +
                ", message=" + message +
                ", currentTime=" + currentTime +
                '}';
    }

    public RestResult(boolean success, Integer code, Object data, String message)
    {
        this.success = success;
        this.code = code;
        this.data = data;
        this.message = message;
        this.currentTime = new Timestamp(System.currentTimeMillis());
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Timestamp getCurrentTime()
    {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime)
    {
        this.currentTime = currentTime;
    }
}
