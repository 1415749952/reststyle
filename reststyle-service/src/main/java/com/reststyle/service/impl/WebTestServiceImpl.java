package com.reststyle.service.impl;

import com.reststyle.mapper.WebTestMapper;
import com.reststyle.service.WebTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-25
 * @Time: 12:47
 */
@Service
public class WebTestServiceImpl implements WebTestService
{

    @Autowired
    private WebTestMapper webTestMapper;

    @Override
    public Map test()
    {
      return webTestMapper.test();
    }
}
