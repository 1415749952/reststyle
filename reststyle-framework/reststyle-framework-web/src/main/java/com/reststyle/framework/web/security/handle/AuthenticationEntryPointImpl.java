package com.reststyle.framework.web.security.handle;

import cn.hutool.core.util.StrUtil;
import com.reststyle.framework.common.unite_response.RestResult;
import com.reststyle.framework.common.unite_response.ResultUtil;
import com.reststyle.framework.common.utils.ServletUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

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
        RestResult error = ResultUtil.error(HttpStatus.UNAUTHORIZED, StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI()));
        ServletUtils.renderString(response,JacksonUtils.object2Json(error),HttpStatus.UNAUTHORIZED);

    }
}
