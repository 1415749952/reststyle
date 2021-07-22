package com.reststyle.framework.domain.dto;

import cn.hutool.core.util.StrUtil;
import com.reststyle.framework.common.constant.GenConstants;
import com.reststyle.framework.domain.table.GenTable;
import com.reststyle.framework.domain.table.GenTableColumn;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-22
 * @Time: 15:03
 */
@Data
public class GenTableDto extends GenTable
{

    /** 主键信息 */
    private GenTableColumn pkColumn;

    /** 子表信息 */
    private GenTableDto subTable;

    /** 表列信息 */
    private List<GenTableColumn> columns;


    /** 树编码字段 */
    private String treeCode;

    /** 树父编码字段 */
    private String treeParentCode;

    /** 树名称字段 */
    private String treeName;

    /** 上级菜单ID字段 */
    private String parentMenuId;

    /** 上级菜单名称字段 */
    private String parentMenuName;



    public GenTableColumn getPkColumn()
    {
        return pkColumn;
    }

    public void setPkColumn(GenTableColumn pkColumn)
    {
        this.pkColumn = pkColumn;
    }

    public GenTableDto getSubTable()
    {
        return subTable;
    }

    public void setSubTable(GenTableDto subTable)
    {
        this.subTable = subTable;
    }

    public List<GenTableColumn> getColumns()
    {
        return columns;
    }

    public void setColumns(List<GenTableColumn> columns)
    {
        this.columns = columns;
    }

    public String getTreeCode()
    {
        return treeCode;
    }

    public void setTreeCode(String treeCode)
    {
        this.treeCode = treeCode;
    }

    public String getTreeParentCode()
    {
        return treeParentCode;
    }

    public void setTreeParentCode(String treeParentCode)
    {
        this.treeParentCode = treeParentCode;
    }

    public String getTreeName()
    {
        return treeName;
    }

    public void setTreeName(String treeName)
    {
        this.treeName = treeName;
    }

    public String getParentMenuId()
    {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId)
    {
        this.parentMenuId = parentMenuId;
    }

    public String getParentMenuName()
    {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName)
    {
        this.parentMenuName = parentMenuName;
    }

    public boolean isSub()
    {
        return isSub(super.getTplCategory());
    }

    public static boolean isSub(String tplCategory)
    {
        return tplCategory != null && StrUtil.equals(GenConstants.TPL_SUB, tplCategory);
    }

    public boolean isTree()
    {
        return isTree(super.getTplCategory());
    }

    public static boolean isTree(String tplCategory)
    {
        return tplCategory != null && StrUtil.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud()
    {
        return isCrud(super.getTplCategory());
    }

    public static boolean isCrud(String tplCategory)
    {
        return tplCategory != null && StrUtil.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField)
    {
        return isSuperColumn(super.getTplCategory(), javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField)
    {
        if (isTree(tplCategory))
        {
            return StrUtil.equalsAnyIgnoreCase(javaField,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StrUtil.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }
}
