package com.reststyle.framework.web.controller;

import com.reststyle.framework.service.WebTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    //@PreAuthorize("hasAuthority('system:user:list')")
    @PreAuthorize("hasPermission('/web/test','system:user:list')")
    public Map test()
    {
        return webTestService.test();
    }
}
