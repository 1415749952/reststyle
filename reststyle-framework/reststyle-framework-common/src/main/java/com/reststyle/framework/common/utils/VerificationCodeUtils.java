package com.reststyle.framework.common.utils;

import com.reststyle.framework.common.constant.Constants;
import com.reststyle.framework.common.utils.redis.RedisUtils;
import com.reststyle.framework.common.utils.spring.SpringUtils;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * Created with IntelliJ IDEA.
 * Description:验证码工具类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-03-02
 * @Time: 12:06
 */
public class VerificationCodeUtils
{

    /**
     * 校验验证码是否正确
     *
     * @param uuid
     * @param code
     */
    public static void validCode(String uuid, String code)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        RedisUtils redisCache = SpringUtils.getBean(RedisUtils.class);
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (null == captcha)
        {
            throw new BadCredentialsException("验证码已失效");
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new BadCredentialsException("验证码错误");
        }
    }
}
