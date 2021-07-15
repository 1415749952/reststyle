package com.reststyle.framework.web.security.handle;

import com.reststyle.framework.common.unite_response.RestResult;
import com.reststyle.framework.common.unite_response.ResultUtil;
import com.reststyle.framework.common.utils.ServletUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description: 登录失败处理类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:36
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler
{
    /**
     * 登录失败返回结果
     *
     * @Author Sans
     * @CreateTime 2019/10/3 9:12
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
    {
        RestResult restResult = ResultUtil.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        ServletUtils.renderString(response, JacksonUtils.object2Json(restResult), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
