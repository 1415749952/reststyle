package com.reststyle.framework.web.security.handle;

import cn.hutool.core.util.StrUtil;
import com.reststyle.framework.common.unite_response.RestResult;
import com.reststyle.framework.common.unite_response.ResultUtil;
import com.reststyle.framework.common.utils.ServletUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:暂无权限处理类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:22
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler
{
    /**
     * 暂无权限返回结果
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
    {
        RestResult restResult = ResultUtil.error(HttpStatus.FORBIDDEN, StrUtil.format("请求访问：{}，没有权限访问系统资源", request.getRequestURI()));
        ServletUtils.renderString(response, JacksonUtils.object2Json(restResult), HttpStatus.FORBIDDEN);
    }
}
