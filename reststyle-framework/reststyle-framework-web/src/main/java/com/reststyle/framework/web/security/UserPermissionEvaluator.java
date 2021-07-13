package com.reststyle.framework.web.security;

import com.reststyle.framework.common.security.entity.SecurityMenu;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.service.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:自定义权限注解验证
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-13
 * @Time: 15:22
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator
{
    @Autowired
    private SecurityService securityService;

    /**
     * hasPermission鉴权方法
     * 这里仅仅判断PreAuthorize注解中的权限表达式
     * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
     * 当然targetUrl不一定是URL可以是数据Id还可以是管理员标识等,这里根据需求自行设计
     *
     * @Author Sans
     * @CreateTime 2019/10/6 18:25
     * @Param authentication  用户身份(在使用hasPermission表达式时Authentication参数默认会自动带上)
     * @Param targetUrl  请求路径
     * @Param permission 请求路径权限
     * @Return boolean 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission)
    {
        // 获取用户信息
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        // 查询用户权限(这里可以将权限放入缓存中提升效率)
        Set<String> permissions = new HashSet<>();
        List<SecurityMenu> securityMenus = securityService.selectSysMenuByUserId(securityUser);
        for (SecurityMenu securityMenu : securityMenus)
        {
            permissions.add(securityMenu.getPerms());
        }
        // 权限对比
        return permissions.contains(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission)
    {
        return false;
    }
}