package com.reststyle.framework.common.utils.id;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.reststyle.framework.common.utils.spring.SpringUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 13:58
 */
public class IdWorker
{
    public static Long getId()
    {
        SequenceAutoConfiguration sequenceAutoConfiguration = SpringUtils.getBean(SequenceAutoConfiguration.class);
        Snowflake snowflake = IdUtil.createSnowflake(sequenceAutoConfiguration.getWorkerId(), sequenceAutoConfiguration.getDatacenterId());
        return snowflake.nextId();
    }

    public static String getStringId()
    {
        return getId().toString();
    }
}