package com.reststyle.web.controller;

import com.reststyle.framework.common.operation_log.BusinessType;
import com.reststyle.framework.common.operation_log.OperLog;
import com.reststyle.framework.common.operation_log.OperUnit;
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
@RequestMapping("/framework/test")
public class Test
{
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
