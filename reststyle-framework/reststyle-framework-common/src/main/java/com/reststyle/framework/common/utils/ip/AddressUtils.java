package com.reststyle.framework.common.utils.ip;

import cn.hutool.core.util.StrUtil;
import com.reststyle.framework.common.constant.Constants;
import com.reststyle.framework.common.utils.http.HttpUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 获取地址类
 * 
 * @author TheFei
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip)
    {
        String address = UNKNOWN;
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "内网IP";
        }
        try
        {
            String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
            if (StrUtil.isEmpty(rspStr))
            {
                log.error("获取地理位置异常 {}", ip);
                return UNKNOWN;
            }
            Map<String, Object> stringObjectMap = JacksonUtils.convertJson2Map(rspStr);
            String region = stringObjectMap.get("pro").toString();
            String city = stringObjectMap.get("city").toString();
            return String.format("%s %s", region, city);
        }
        catch (Exception e)
        {
            log.error("获取地理位置异常 {}", ip);
        }
        return address;
    }
}
