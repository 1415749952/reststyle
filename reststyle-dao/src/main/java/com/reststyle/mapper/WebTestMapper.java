package com.reststyle.mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-25
 * @Time: 12:51
 */
@Repository
public class WebTestMapper
{
    public Map<String, String> test()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName","zhangsan");
        map.put("name","张三");
        map.put("password","123456");
        return map;
    }
}
