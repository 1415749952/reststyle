package com.reststyle.framework.service.generator.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reststyle.framework.domain.table.GenTableColumn;
import com.reststyle.framework.mapper.business.GenTableColumnMapper;
import com.reststyle.framework.service.generator.GenTableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements GenTableColumnService
{

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Override
    public List<GenTableColumn> selectDbTableColumnsByName(String tableName)
    {
        return genTableColumnMapper.selectDbTableColumnsByName(tableName);
    }

    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId)
    {
        LambdaQueryWrapper<GenTableColumn> queryWrapper = new QueryWrapper<GenTableColumn>().lambda()
                .eq(GenTableColumn::getTableId, tableId);
        return this.list(queryWrapper);
    }

    @Override
    public int deleteGenTableColumnByIds(Long[] tableIds)
    {
        return genTableColumnMapper.deleteGenTableColumnByIds(tableIds);
    }


}