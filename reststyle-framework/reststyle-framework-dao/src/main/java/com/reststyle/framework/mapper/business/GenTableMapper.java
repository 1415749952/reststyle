package com.reststyle.framework.mapper.business;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reststyle.framework.domain.bo.GenTableQueryBo;
import com.reststyle.framework.domain.table.GenTable;
import com.reststyle.framework.domain.vo.GenTableVo;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable>
{
    List<GenTableVo> selectDbTableList(GenTableQueryBo genTableQueryBo);
}