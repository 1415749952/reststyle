package com.reststyle.framework.controller;

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
    public Map test()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName","zhangsan");
        map.put("name","张三");
        map.put("password","123456");
        return map;
    }
}
