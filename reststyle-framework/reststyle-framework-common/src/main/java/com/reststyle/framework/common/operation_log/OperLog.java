package com.reststyle.framework.common.operation_log;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:自定义注解
 * <p>
 * 注解使用示例@OperationLogDetail(detail = "获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:22
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OperLog
{
    /**
     * 接口描述
     */
    String methodDetail() default "";

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 记录操作的业务类型(enum):主要是select,insert,update,delete
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    OperUnit operUnit() default OperUnit.UNKNOWN;
}