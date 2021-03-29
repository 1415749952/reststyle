package com.reststyle.framework.common.exception;
/**
 * Created with IntelliJ IDEA.
 * Description: from表单重复提交异常
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-12
 * @Time: 20:49
 */

public class FormRepeatException extends RuntimeException
{
	public FormRepeatException()
	{
		super();
	}

	public FormRepeatException(String message)
	{
		super(message);
	}

	public FormRepeatException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public FormRepeatException(Throwable cause)
	{
		super(cause);
	}

	protected FormRepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
