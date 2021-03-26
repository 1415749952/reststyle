package com.reststyle.framework.common.utils.id;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 20:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "snowflake")
public class SequenceAutoConfiguration
{
    /**
     * 数据中心id
     */
    private Long datacenterId;

    /**
     * 机器id
     */
    private Long workerId;

}
