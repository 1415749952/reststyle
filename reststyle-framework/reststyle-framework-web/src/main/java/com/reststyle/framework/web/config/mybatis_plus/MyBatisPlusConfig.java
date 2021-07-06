package com.reststyle.framework.web.config.mybatis_plus;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-04-06
 * @Time: 17:41
 */
@Configuration
public class MyBatisPlusConfig
{
    /**
     * 自动填充功能
     *
     * @return
     */
    @Bean
    public GlobalConfig globalConfig()
    {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MetaHandler());
        return globalConfig;
    }
}
