package com.reststyle.framework.web.security.handle;

import com.reststyle.framework.common.unite_response.ResultUtil;
import com.reststyle.framework.common.utils.ServletUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:登出成功处理类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:22
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler
{
  /*  @Autowired
    private TokenService tokenService;
*/
    /**
     * 用户登出返回结果
     * 这里应该让前端清除掉Token
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        /*LoginUser loginUser = tokenService.getLoginUser(request);
        if (Validator.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            //tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }*/
        String restResult = JacksonUtils.object2Json(ResultUtil.success("退出成功"));
        ServletUtils.renderString(response, JacksonUtils.object2Json(restResult), HttpStatus.OK);
    }
}
