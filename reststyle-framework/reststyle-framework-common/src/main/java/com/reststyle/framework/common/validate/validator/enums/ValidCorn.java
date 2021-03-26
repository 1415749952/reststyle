package com.reststyle.framework.common.validate.validator.enums;

import com.reststyle.framework.common.validate.validator.CornValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * Description: 校验corn表达式是否合法
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 11:02
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {CornValidator.class})
public @interface ValidCorn
{
    String message() default "corn表达式不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
