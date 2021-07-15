package com.reststyle.framework.web.security;

import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.service.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:自定义权限注解验证
 * 用法：在类上面加上注解@PreAuthorize("hasPermission('/web/test','system:user:list')")
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
        // 获取用户信息(这里的登录用户信息已经在JwtAuthenticationTokenFilter中从数据库查询出来了)
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        // 查询用户权限(这里可以将权限放入缓存中提升效率)
        //Set<String> permissions = new HashSet<>();
        //List<String> dbPermissions = securityService.selectPermissionByUserId(securityUser);
        // 权限对比
        Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) securityUser.getAuthorities();
        return authorities.contains(new SimpleGrantedAuthority(permission.toString()));
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission)
    {
        return false;
    }
}