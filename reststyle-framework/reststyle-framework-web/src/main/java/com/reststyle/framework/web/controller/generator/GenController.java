package com.reststyle.framework.web.controller.generator;

import cn.hutool.core.convert.Convert;
import com.reststyle.framework.common.oper_log.BusinessType;
import com.reststyle.framework.common.unite_response.RestResult;
import com.reststyle.framework.domain.table.GenTable;
import com.reststyle.framework.domain.table.GenTableColumn;
import com.reststyle.framework.service.generator.GenTableColumnService;
import com.reststyle.framework.service.generator.GenTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 代码生成 操作处理
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-21
 * @Time: 15:17
 */
@Api(tags="代码生成模块")
@RestController
@RequestMapping("/tool/gen")
public class GenController
{

    @Autowired
    private GenTableService genTableService;

    @Autowired
    private GenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @ApiOperation(value = "查询代码生成列表")
    @PreAuthorize("hasAuthority('tool:gen:list')")
    @GetMapping("/list")
    public RestResult genList(GenTable genTable)
    {
       /* startPage();
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return getDataTable(list);*/
        return null;
    }

    /**
     * 修改代码生成业务
     */
    @ApiOperation(value = "修改代码生成业务")
    @PreAuthorize("hasAuthority('tool:gen:query')")
    @GetMapping(value = "/{talbleId}")
    public RestResult getInfo(@PathVariable Long talbleId)
    {
       /* GenTable table = genTableService.selectGenTableById(talbleId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return RestResult.success(map);*/
        return null;
    }

    /**
     * 查询数据库列表
     */
    @ApiOperation(value = "查询数据库已有数据表的列表集合")
    @PreAuthorize("hasAuthority('tool:gen:list')")
    @GetMapping("/db/list")
    public RestResult dataList(GenTable genTable)
    {
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据表字段列表
     */
    @ApiOperation(value = "查询数据表字段列表")
    @PreAuthorize("hasAuthority('tool:gen:list')")
    @GetMapping(value = "/column/{talbleId}")
    public RestResult columnList(Long tableId)
    {
       /* RestResult dataInfo = new RestResult();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;*/
        return null;
    }

    /**
     * 导入表结构（保存）
     */
    @ApiOperation(value = "导入表结构（保存）")
    @PreAuthorize("hasAuthority('tool:gen:list')")
    //@Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public RestResult importTableSave(String tables)
    {
      /*  String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return RestResult.success();*/
        return null;
    }

    /**
     * 修改保存代码生成业务
     */
    @ApiOperation(value = "修改保存代码生成业务")
    @PreAuthorize("hasAuthority('tool:gen:edit')")
    //@Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public RestResult editSave(@Validated @RequestBody GenTable genTable)
    {
       /* genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return RestResult.success();*/
        return null;
    }

    /**
     * 删除代码生成
     */
    @ApiOperation(value = "删除代码生成")
    @PreAuthorize("hasAuthority('tool:gen:remove')")
    //@Log(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public RestResult remove(@PathVariable Long[] tableIds)
    {
       /* genTableService.deleteGenTableByIds(tableIds);
        return RestResult.success();*/
        return null;
    }

    /**
     * 预览代码
     */
    @ApiOperation(value = "预览代码")
    @PreAuthorize("hasAuthority('tool:gen:preview')")
    @GetMapping("/preview/{tableId}")
    public RestResult preview(@PathVariable("tableId") Long tableId) throws IOException
    {
       /* Map<String, String> dataMap = genTableService.previewCode(tableId);
        return RestResult.success(dataMap);*/
        return null;
    }

    /**
     * 生成代码（下载方式）
     */
    @ApiOperation(value = "生成代码（下载方式）")
    @PreAuthorize("hasAuthority('tool:gen:code')")
    //@Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
       /* byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);*/
    }

    /**
     * 生成代码（自定义路径）
     */
    @ApiOperation(value = "生成代码（自定义路径）")
    @PreAuthorize("hasAuthority('tool:gen:code')")
    //@Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public RestResult genCode(@PathVariable("tableName") String tableName)
    {
       /* genTableService.generatorCode(tableName);
        return RestResult.success();*/
        return null;
    }

    /**
     * 同步数据库
     */
    @ApiOperation(value = "同步数据库")
    @PreAuthorize("hasAuthority('tool:gen:edit')")
    //@Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/synchDb/{tableName}")
    public RestResult synchDb(@PathVariable("tableName") String tableName)
    {
       /* genTableService.synchDb(tableName);
        return RestResult.success();*/
        return null;
    }

    /**
     * 批量生成代码
     */
    @ApiOperation(value = "批量生成代码")
    @PreAuthorize("hasAuthority('tool:gen:code')")
    //@Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException
    {
       /* String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);*/
        return ;
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
