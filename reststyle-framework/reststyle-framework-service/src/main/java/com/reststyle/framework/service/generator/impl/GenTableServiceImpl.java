package com.reststyle.framework.service.generator.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reststyle.framework.common.exception.BusinessException;
import com.reststyle.framework.common.security.SecurityUtils;
import com.reststyle.framework.common.unite_response.RestResult;
import com.reststyle.framework.domain.bo.GenTableQueryBo;
import com.reststyle.framework.domain.table.GenTable;
import com.reststyle.framework.domain.table.GenTableColumn;
import com.reststyle.framework.domain.vo.GenTableVo;
import com.reststyle.framework.mapper.business.GenTableMapper;
import com.reststyle.framework.service.generator.GenTableColumnService;
import com.reststyle.framework.service.generator.GenTableService;
import com.reststyle.framework.service.generator.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-21
 * @Time: 15:37
 */
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements GenTableService
{

    @Autowired
    private GenTableMapper genTableMapper;

    @Autowired
    private GenTableColumnService genTableColumnService;


    @Override
    public PageInfo<GenTableVo> selectDbTableList(GenTableQueryBo genTableQueryBo)
    {
        PageHelper.startPage(genTableQueryBo.getPageNum(), genTableQueryBo.getPageSize());
        List<GenTableVo> genTables = genTableMapper.selectDbTableList(genTableQueryBo);
        return new PageInfo<>(genTables);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importTableSave(String tables)
    {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = this.selectDbTableListByNames(tableNames);
        try
        {
            for (GenTable table : tableList)
            {
                String tableName = table.getTableName();
                GenUtils.initTable(table);
                boolean save = this.save(table);
                if (save)
                {
                    // 保存列信息
                    List<GenTableColumn> genTableColumns = genTableColumnService.selectDbTableColumnsByName(tableName);
                    for (GenTableColumn column : genTableColumns)
                    {
                        GenUtils.initColumnField(column, table);
                        genTableColumnService.save(column);
                    }
                }
                System.out.println(table);
            }
        }
        catch (Exception e)
        {
            throw new BusinessException("表格导入失败：" + e.getMessage());
        }
    }


    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNames)
    {
        return genTableMapper.selectDbTableListByNames(tableNames);
    }
    /**
     * 删除业务对象
     *
     * @param tableIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGenTableByIds(Long[] tableIds)
    {
        genTableMapper.deleteGenTableByIds(tableIds);
        genTableColumnService.deleteGenTableColumnByIds(tableIds);
    }


}
