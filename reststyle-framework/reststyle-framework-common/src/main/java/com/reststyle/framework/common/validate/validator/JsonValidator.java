package com.reststyle.framework.common.validate.validator;

import com.reststyle.framework.common.utils.json.JacksonUtils;
import com.reststyle.framework.common.validate.validator.enums.ValidJson;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 11:28
 */
public class JsonValidator implements ConstraintValidator<ValidJson, String>
{

    @Override
    public void initialize(ValidJson constraintAnnotation)
    {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if (StringUtils.isBlank(value))
        {
            return true;
        }
        else
        {
            if (!JacksonUtils.isJson(value))
            {
                return false;
            }
        }
        return true;
    }
}
