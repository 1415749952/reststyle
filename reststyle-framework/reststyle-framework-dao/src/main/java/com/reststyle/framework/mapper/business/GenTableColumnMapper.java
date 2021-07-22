package com.reststyle.framework.mapper.business;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reststyle.framework.domain.table.GenTableColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-21
 * @Time: 15:39
 */
@Mapper
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn>
{
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

    int deleteGenTableColumnByIds(Long[] tableIds);
}