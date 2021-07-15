package com.reststyle.framework.common.security.entity;

import com.reststyle.framework.common.exception.BusinessException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户登录实体类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:22
 */
@Data
public class LoginBody
{
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

    public static void valid(LoginBody loginBody)
    {
        if(null == loginBody)
        {
            throw new BadCredentialsException("登录信息输入为空");
        }
        if (StringUtils.isBlank(loginBody.getUsername()))
        {
            throw new BadCredentialsException("用户名为空");
        }
        if (StringUtils.isBlank(loginBody.getPassword()))
        {
            throw new BadCredentialsException("密码为空");
        }
        if (StringUtils.isBlank(loginBody.getCode()))
        {
            throw new BadCredentialsException("验证码为空");
        }
        if (StringUtils.isBlank(loginBody.getUuid()))
        {
            throw new BadCredentialsException("定位验证码UUID为空");
        }
    }
}
