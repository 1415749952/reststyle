package com.reststyle.framework.service.generator;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reststyle.framework.domain.table.GenTableColumn;

import java.util.List;

/**
*  Created with IntelliJ IDEA.
*  Description:
*  @version 1.0
*  @author: TheFei
*  @Date: 2021-07-21
*  @Time: 15:39
*
*/
public interface GenTableColumnService extends IService<GenTableColumn>
{


    List<GenTableColumn> selectDbTableColumnsByName(String tableName);
}
