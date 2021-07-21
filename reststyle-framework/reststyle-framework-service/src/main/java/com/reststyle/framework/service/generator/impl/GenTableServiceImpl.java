package com.reststyle.framework.service.generator.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reststyle.framework.domain.table.GenTable;
import com.reststyle.framework.mapper.business.GenTableMapper;
import com.reststyle.framework.service.generator.GenTableService;
import org.springframework.stereotype.Service;

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
}
