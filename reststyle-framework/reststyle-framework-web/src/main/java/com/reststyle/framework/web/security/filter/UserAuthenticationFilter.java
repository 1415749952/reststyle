package com.reststyle.framework.web.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reststyle.framework.common.constant.Constants;
import com.reststyle.framework.common.exception.BusinessException;
import com.reststyle.framework.common.security.entity.LoginBody;
import com.reststyle.framework.common.utils.VerificationCodeUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import com.reststyle.framework.common.utils.redis.RedisUtils;
import com.reststyle.framework.common.utils.spring.SpringUtils;
import com.reststyle.framework.service.manager.AsyncManager;
import com.reststyle.framework.service.manager.factory.AsyncFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:用户名密码通过JSON的方式进行传递，则需要自定义相关过滤器，
 * 通过分析源码我们发现，默认的用户名密码提取在UsernamePasswordAuthenticationFilter过滤器
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:22
 */
@Slf4j
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType()) && request.getMethod().equals("POST"))
        {
            LoginBody loginBody = null;
            try (InputStream is = request.getInputStream())
            {
                loginBody = JacksonUtils.json2Object(is,LoginBody.class);
                //校验登录信息
                try
                {
                    LoginBody.valid(loginBody);
                }
                catch (BadCredentialsException e)
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginBody.getUsername(), Constants.LOGIN_FAIL, e.getMessage()));
                    throw new BadCredentialsException(e.getMessage());
                }
            }
            catch (IOException e)
            {
                log.error(e.getMessage());
            }

            //校验验证码
            try
            {
                VerificationCodeUtils.validCode(loginBody.getUuid(),loginBody.getCode());
            }
            catch (BadCredentialsException e)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginBody.getUsername(), Constants.LOGIN_FAIL, e.getMessage()));
                throw new BadCredentialsException(e.getMessage());
            }

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        else
        {
            return super.attemptAuthentication(request, response);
        }
    }




}
