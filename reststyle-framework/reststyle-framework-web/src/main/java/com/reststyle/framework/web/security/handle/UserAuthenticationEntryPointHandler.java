package com.reststyle.framework.web.security.handle;

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

/**
 * Created with IntelliJ IDEA.
 * Description:用户未登录处理类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:32
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint
{
    /**
     * 用户未登录返回结果
     *
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
    {
        RestResult restResult = ResultUtil.error(HttpStatus.UNAUTHORIZED, "未登录");
        ServletUtils.renderString(response, JacksonUtils.object2Json(restResult), HttpStatus.UNAUTHORIZED);
    }
}
