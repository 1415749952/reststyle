package com.reststyle.framework.common.exception.unite_exception;


import org.springframework.security.core.AuthenticationException;

/**
 * Created with IntelliJ IDEA.
 * Description: 图形验证异常类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-09
 * @Time: 11:23
 */
public class ImageCodeException extends AuthenticationException
{

    public ImageCodeException(String msg, Throwable t)
    {
        super(msg, t);
    }

    public ImageCodeException(String msg)
    {
        super(msg);
    }
}
