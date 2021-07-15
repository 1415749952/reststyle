package com.reststyle.framework.swagger.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-14
 * @Time: 16:19
 */
@RestController
@RequestMapping("/a")
public class AController
{
    @GetMapping("/b")
    public Map test()
    {
        HashMap hashMap = new HashMap();
        hashMap.put("name","zhangsan");
        return hashMap;
    }
    @PostMapping("/c")
    public A test(@RequestBody A a)
    {
        return a;
    }
}
