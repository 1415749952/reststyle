package com.reststyle.framework.web.config.mybatis_plus;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-04-06
 * @Time: 17:50
 */

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.reststyle.framework.common.enums.DelFlag;
import com.reststyle.framework.common.security.SecurityUtils;
import com.reststyle.framework.common.security.model.LoginUser;
import com.reststyle.framework.common.utils.DateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:新增和更新的基础数据填充，配合MyBatisPlusConfig使用
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-04-06
 * @Time: 17:44
 */
@Component
public class MetaHandler implements MetaObjectHandler
{

    private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);

    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        this.setFieldValByName("delFlag", DelFlag.LOGIC_NOT_DELETE_VALUE.getValue(), metaObject);
        this.setFieldValByName("createTime", DateUtils.getNowDate(), metaObject);
        this.setFieldValByName("createBy", loginUser.getUsername(), metaObject);
        this.setFieldValByName("updateTime", DateUtils.getNowDate(), metaObject);
        this.setFieldValByName("updateBy", loginUser.getUsername(), metaObject);
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        this.setFieldValByName("updateTime", DateUtils.getNowDate(), metaObject);
        this.setFieldValByName("updateBy", loginUser.getUsername(), metaObject);
    }
}