package com.reststyle.framework.common.utils.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-03-02
 * @Time: 12:06
 */
@Slf4j
public class JacksonUtils
{
    private static final ObjectMapper objectMapper;

    static
    {

        objectMapper = new ObjectMapper();
        //序列化时候统一日期格式
        //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //设置null时候不序列化(只针对对象属性)
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //单引号处理
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

    }


    /**
     * 将jsonStr转成相应的Map
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> convertJson2Map(String jsonStr)
    {
        MapLikeType mapLikeType = JacksonUtils.objectMapper.getTypeFactory().constructMapLikeType(HashMap.class, String.class, Object.class);
        try
        {
            return objectMapper.readValue(jsonStr, mapLikeType);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将json转成相应的List
     *
     * @param jsonStr
     * @return
     */
    public static List convertJson2List(String jsonStr)
    {
        JavaType javaType = JacksonUtils.objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Object.class);
        try
        {
            return objectMapper.readValue(jsonStr, javaType);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 反序列化json
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String json, Class<T> clazz)
    {
        try
        {
            return objectMapper.readValue(json, clazz);
        }
        catch (JsonParseException e)
        {
            log.error(e.getMessage(), e);
        }
        catch (JsonMappingException e)
        {
            log.error(e.getMessage(), e);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 反序列化json，将流变成对象
     *
     * @param inputStream
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Object(InputStream inputStream, Class<T> clazz)
    {
        try
        {
            return objectMapper.readValue(inputStream, clazz);
        }
        catch (JsonParseException e)
        {
            log.error(e.getMessage(), e);
        }
        catch (JsonMappingException e)
        {
            log.error(e.getMessage(), e);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 序列化java对象
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> String object2Json(T entity)
    {
        try
        {
            return objectMapper.writeValueAsString(entity);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 判断一个字符串是不是json字符串
     *
     * @param jsonString
     * @return
     */
    public static boolean isJson(String jsonString)
    {
        if (StringUtils.isBlank(jsonString))
        {
            return false;
        }
        else
        {
            if (convertJson2Map(jsonString) == null && convertJson2List(jsonString) == null)
            {
                return false;
            }
        }
        return true;
    }
}
