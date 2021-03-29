package com.reststyle.framework.mapper.business;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reststyle.framework.domain.table.SysLogininfor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *  Created with IntelliJ IDEA.
 *  Description:
 *  @version 1.0
 *  @author: TheFei
 *  @Date: 2021-03-29
 *  @Time: 9:25
 * 
 */
@Mapper
@Repository
public interface SysLogininforMapper extends BaseMapper<SysLogininfor>
{
}