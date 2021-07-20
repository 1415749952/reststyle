package com.reststyle.framework.web.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.reststyle.framework.common.enums.DelFlag;
import com.reststyle.framework.common.security.SecurityUtils;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-20
 * @Time: 14:56
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler
{

    @Override
    public void insertFill(MetaObject metaObject)
    {
        Date nowDate = DateUtils.getNowDate();
        SecurityUser loginUser = SecurityUtils.getLoginUser();
        // 插入创建时间
        if (metaObject.hasSetter("createTime"))
        {
            this.strictInsertFill(metaObject, "createTime", Date.class, nowDate);
        }
        // 插入创建人
        if (metaObject.hasSetter("createBy"))
        {
            this.strictInsertFill(metaObject, "createBy", String.class, String.valueOf(loginUser.getUserId()));
        }
        // 插入软删除标志
        if (metaObject.hasSetter("delFlag"))
        {
            this.strictInsertFill(metaObject, "delFlag", String.class, DelFlag.LOGIC_NOT_DELETE_VALUE.getValue());
        }
        // 插入更新时间
        if (metaObject.hasSetter("updateTime"))
        {
            this.strictUpdateFill(metaObject, "updateTime",Date.class, nowDate);
        }
        // 插入更新人
        if (metaObject.hasSetter("updateBy"))
        {
            this.strictUpdateFill(metaObject, "updateBy", String.class, String.valueOf(loginUser.getUserId()));
        }

    }

    @Override
    public void updateFill(MetaObject metaObject)
    {
        Date nowDate = DateUtils.getNowDate();
        SecurityUser loginUser = SecurityUtils.getLoginUser();
        // 插入更新时间
        if (metaObject.hasSetter("updateTime"))
        {
            this.strictUpdateFill(metaObject, "updateTime",Date.class, nowDate);
        }
        // 插入更新人
        if (metaObject.hasSetter("updateBy"))
        {
            this.strictUpdateFill(metaObject, "updateBy", String.class, String.valueOf(loginUser.getUserId()));
        }

    }
}