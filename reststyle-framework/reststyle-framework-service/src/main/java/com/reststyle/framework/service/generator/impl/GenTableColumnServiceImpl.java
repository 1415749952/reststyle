package com.reststyle.framework.service.generator.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reststyle.framework.domain.table.GenTableColumn;
import com.reststyle.framework.mapper.business.GenTableColumnMapper;
import com.reststyle.framework.service.generator.GenTableColumnService;
import org.springframework.stereotype.Service;

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

}