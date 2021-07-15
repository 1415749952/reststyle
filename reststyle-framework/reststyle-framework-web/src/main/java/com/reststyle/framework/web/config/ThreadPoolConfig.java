package com.reststyle.framework.web.config;

import com.reststyle.framework.common.utils.thread.ThreadsUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author ruoyi
 **/
@Configuration
public class ThreadPoolConfig
{
    // 核心线程池大小
    private int corePoolSize = 50;

    // 最大可创建的线程数
    private int maxPoolSize = 200;

    // 队列最大长度
    private int queueCapacity = 1000;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds = 300;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService()
    {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build())
        {
            @Override
            protected void afterExecute(Runnable r, Throwable t)
            {
                super.afterExecute(r, t);
                ThreadsUtils.printException(r, t);
            }
        };
    }
}
/**
 * anyRequest          |   匹配所有请求路径
 * access              |   SpringEl表达式结果为true时可以访问
 * anonymous           |   匿名可以访问
 * denyAll             |   用户不能访问
 * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
 * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
 * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
 * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
 * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
 * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
 * permitAll           |   用户可以任意访问
 * rememberMe          |   允许通过remember-me登录的用户访问
 * authenticated       |   用户登录后可访问
 */