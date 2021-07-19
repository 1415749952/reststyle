package com.reststyle.framework.web.controller;

import com.reststyle.framework.common.oper_log.BusinessType;
import com.reststyle.framework.common.oper_log.OperLog;
import com.reststyle.framework.common.oper_log.OperUnit;
import com.reststyle.framework.service.WebTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/web")
public class WebTest
{
    @Autowired
    private WebTestService webTestService;

    /**
     * 测试权限
     * @return
     */
    @GetMapping("/test")
    //@PreAuthorize("hasAuthority('system:user:list')")
    @PreAuthorize("hasPermission('/web/test','system:user:list')")
    public Map test()
    {
        return webTestService.test();
    }

    /**
     * 测试记录日志
     * @param userName
     * @return
     * @throws Exception
     */
    @GetMapping("/test1")
    @OperLog(methodDetail = "framework-web模块测试方法",businessType = BusinessType.SELECT,operUnit = OperUnit.USER)
    public Map test1(String userName) throws Exception
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName","zhangsan");
        map.put("name",userName);
        map.put("password","123456");
        if (userName.equals("张山"))
        {
            throw new Exception("出错了");
        }
        return map;
    }


    @Cacheable(value = "ceshi", key = "'user:'+#id", unless = "#result==null")
    @GetMapping("{id}")
    public Map getUserById(@PathVariable("id") Long id) {
        Map user = new HashMap();
        user.put("id",id);
        user.put("age",20);
        user.put("username","张三");
        user.put("password","123456");
        user.put("token","2223334455:333:332");
        user.put("id",id);
        return user;
    }

    @CacheEvict(cacheNames = "login", key = "")
    @GetMapping("test/{id}")
    public Map getUserById1(@PathVariable("id") Long id) {
        Map user = new HashMap();
        user.put("id",id);
        user.put("age",20);
        user.put("username","张三");
        user.put("password","123456");
        user.put("token","2223334455:333:332");
        user.put("id",id);
        return user;
    }
}
