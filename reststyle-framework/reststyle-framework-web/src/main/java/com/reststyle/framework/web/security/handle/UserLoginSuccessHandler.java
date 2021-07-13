package com.reststyle.framework.web.security.handle;

import com.reststyle.framework.common.constant.Constants;
import com.reststyle.framework.common.unite_response.ResultUtil;
import com.reststyle.framework.common.utils.ServletUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:登录成功处理类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:40
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler
{
    /**
     * 登录成功返回结果
     *
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        // 生成令牌
        String token = "token------写死的token";
        HashMap<String, String> tokenMap = new HashMap<>(1);
        tokenMap.put(Constants.TOKEN, token);
        ServletUtils.renderString(response, JacksonUtils.object2Json(ResultUtil.success(tokenMap)), HttpStatus.OK);
    }
}
