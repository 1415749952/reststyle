package com.reststyle.framework.common.validate.validator;

import com.reststyle.framework.common.utils.cron.CronExpression;
import com.reststyle.framework.common.validate.validator.enums.ValidCorn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * Description:Cron表达式校验
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 17:17
 */
public class CornValidator implements ConstraintValidator<ValidCorn, String>
{

    @Override
    public void initialize(ValidCorn constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (!CronExpression.isValidExpression(value))
        {
            return false;
        }
        return true;
    }
}
