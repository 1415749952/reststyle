package com.reststyle.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reststyle.framework.domain.table.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-26
 * @Time: 19:09
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole>
{
}