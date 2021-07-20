package com.reststyle.framework.web.controller;

import com.reststyle.framework.common.oper_log.BusinessType;
import com.reststyle.framework.common.oper_log.OperLog;
import com.reststyle.framework.common.oper_log.OperUnit;
import com.reststyle.framework.domain.table.SysUser;
import com.reststyle.framework.service.WebTestService;
import com.reststyle.framework.service.business.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-25
 * @Time: 8:49
 */
@Api(tags = "测试用例接口")
@RestController
@RequestMapping("/web")
public class WebTest
{
    @Autowired
    private WebTestService webTestService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 测试权限
     *
     * @return
     */
    @ApiOperation(value = "测试接口权限注解")
    @GetMapping("/test/permission")
    //@PreAuthorize("hasAuthority('system:user:list')")
    @PreAuthorize("hasPermission('/web/test/permission','system:user:list')")
    public Map testPermission()
    {
        return webTestService.test();
    }

    /**
     * 测试记录日志
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "测试接口日志记录注解")
    @GetMapping("/test/operLog")
    @OperLog(methodDetail = "framework-web模块测试方法", businessType = BusinessType.SELECT, operUnit = OperUnit.USER)
    public Map testOperLog(String userName) throws Exception
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", "zhangsan");
        map.put("name", userName);
        map.put("password", "123456");
        if (userName.equals("张山"))
        {
            throw new Exception("出错了");
        }
        return map;
    }

    /**
     * 测试增加缓存
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "测试增加redis缓存注解")
    @Cacheable(value = "test", key = "'user:'+#username", unless = "#result==null")
    @GetMapping("/test/addRedis")
    public Map getUserByUsername(String username) throws Exception
    {
        if (null == username)
        {
            throw new Exception("出错了");
        }
        Map user = new HashMap();
        user.put("id", "12897432983247923");
        user.put("age", 20);
        user.put("username", username);
        user.put("password", "123456");
        user.put("token", "2223334455:333:332");
        return user;
    }

    /**
     * 调用此接口去会去除redis缓存
     * *  @CacheEvict(value = "login", key = "'user:'+#username")
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "测试去掉redis缓存注解")
    //@CacheEvict(value = "login", key = "'user:'+#username")
    @CacheEvict(value = "test", key = "'user:'+#username")
    @GetMapping("test/removeRedis")
    public Map removeUserByUsername(String username) throws Exception
    {
        if (null == username)
        {
            throw new Exception("出错了");
        }
        Map user = new HashMap();

        user.put("message", "成功移除缓存");
        user.put("id", "id663636");
        user.put("age", 20);
        user.put("username", username);
        user.put("password", "123456");
        user.put("token", "2223334455:333:332");
        return user;
    }
    /**
     * 调用此接口去会去除redis缓存
     * *  @CacheEvict(value = "login", key = "'user:'+#username")
     *
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "测试mybatis自动添加create_time和Create_by字段值")
    @PostMapping("test/mybatis")
    public Map testMybatis(@RequestBody SysUser sysUser)
    {
        sysUserService.save(sysUser);
        HashMap hashMap = new HashMap();
        hashMap.put("sysUser",sysUser);
        return hashMap;
    }

}
