package com.reststyle.framework.service.generator;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.reststyle.framework.domain.bo.GenTableQueryBo;
import com.reststyle.framework.domain.table.GenTable;
import com.reststyle.framework.domain.vo.GenTableVo;

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
public interface GenTableService extends IService<GenTable>
{

    PageInfo<GenTableVo> selectDbTableList(GenTableQueryBo genTableQueryBo);

    void importTableSave(String tables);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);
    /**
     * 删除业务对象
     *
     * @param tableIds 需要删除的数据ID
     * @return 结果
     */
    void deleteGenTableByIds(Long[] tableIds);

    PageInfo<GenTableVo> selectGenTableList(GenTableQueryBo genTableQueryBo);
}
