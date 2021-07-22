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
}
