package com.reststyle.framework.web.controller;

import com.reststyle.framework.common.oper_log.BusinessType;
import com.reststyle.framework.common.oper_log.OperLog;
import com.reststyle.framework.common.oper_log.OperUnit;
import com.reststyle.framework.service.WebTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/web/test")
public class WebTest
{
    @Autowired
    private WebTestService webTestService;

    /**
     * 测试权限
     * @return
     */
    @GetMapping
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
    @GetMapping
    @OperLog(methodDetail = "framework-web模块测试方法",businessType = BusinessType.SELECT,operUnit = OperUnit.USER)
    public Map test(String userName) throws Exception
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
}
