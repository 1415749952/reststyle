package com.reststyle.framework.service.generator.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reststyle.framework.domain.bo.GenTableQueryBo;
import com.reststyle.framework.domain.table.GenTable;
import com.reststyle.framework.domain.vo.GenTableVo;
import com.reststyle.framework.mapper.business.GenTableMapper;
import com.reststyle.framework.service.generator.GenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Created with IntelliJ IDEA.
 *  Description:
 *  @version 1.0
 *  @author: TheFei
 *  @Date: 2021-07-21
 *  @Time: 15:37
 * 
 */
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements GenTableService
{

    @Autowired
    private GenTableMapper genTableMapper;


    @Override
    public PageInfo<GenTableVo> selectDbTableList(GenTableQueryBo genTableQueryBo)
    {
        PageHelper.startPage(genTableQueryBo.getPageNum(),genTableQueryBo.getPageSize());
        List<GenTableVo> genTables = genTableMapper.selectDbTableList(genTableQueryBo);
        return new PageInfo(genTables);
    }
}
