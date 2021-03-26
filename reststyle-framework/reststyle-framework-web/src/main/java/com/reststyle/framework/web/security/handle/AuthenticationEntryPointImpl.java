package com.reststyle.framework.web.security.handle;

import com.reststyle.framework.common.unite_response.ResultCode;
import com.reststyle.framework.common.unite_response.ResultUtil;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

/**
 * 认证失败处理类 返回未授权
 * 
 * @author TheFei
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException
    {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(Objects.requireNonNull(JacksonUtils.object2Json(ResultUtil.error(ResultCode.USER_NOT_LOGGED_IN, ResultCode.USER_NOT_LOGGED_IN.getMessage()))));
    }
}
