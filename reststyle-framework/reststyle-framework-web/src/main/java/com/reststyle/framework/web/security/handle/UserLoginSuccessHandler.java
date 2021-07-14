package com.reststyle.framework.web.security.handle;

import com.reststyle.framework.common.constant.Constants;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.common.unite_response.ResultUtil;
import com.reststyle.framework.common.utils.ServletUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import com.reststyle.framework.common.utils.spring.SpringUtils;
import com.reststyle.framework.service.manager.AsyncManager;
import com.reststyle.framework.service.manager.factory.AsyncFactory;
import com.reststyle.framework.service.security.TokenService;
import com.reststyle.framework.web.config.JWTConfig;
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
        SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
        // 组装JWT - accessToken
        String accessToken = SpringUtils.getBean(TokenService.class).createAccessToken(securityUser,JWTConfig.accessTokenExpiration,JWTConfig.secret);
        accessToken = JWTConfig.accessTokenPrefix + accessToken;

         // 组装JWT - refreshToken
        String refreshToken = SpringUtils.getBean(TokenService.class).createRefreshToken(securityUser,JWTConfig.refreshTokenExpiration,JWTConfig.secret);
        refreshToken = JWTConfig.refreshTokenPrefix + refreshToken;


        HashMap<String, String> tokenMap = new HashMap<>(3);
        tokenMap.put("username",securityUser.getUsername());
        tokenMap.put(Constants.ACCESS_TOKEN, accessToken);
        tokenMap.put(Constants.REFRESH_TOKEN, refreshToken);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(securityUser.getUsername(), Constants.LOGIN_SUCCESS, "登陆成功"));
        ServletUtils.renderString(response, JacksonUtils.object2Json(ResultUtil.success(tokenMap)), HttpStatus.OK);
    }
}
