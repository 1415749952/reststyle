package com.reststyle.framework.common.exception.unite_exception;

/**
 * Created with IntelliJ IDEA.
 * Description:处理所有业务异常
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-05
 * @Time: 14:43
 */
public class BusinessException extends RuntimeException
{

    public BusinessException()
    {
        super();
    }

    public BusinessException(String message)
    {
        super(message);
    }
}
