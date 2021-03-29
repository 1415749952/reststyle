package com.reststyle.framework.common.validate.validator.enums;

import com.reststyle.framework.common.validate.validator.JsonValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * Description: 校验是不是json字符串
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 11:01
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {JsonValidator.class})
public @interface ValidJson
{
    String message() default "json数据格式不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
