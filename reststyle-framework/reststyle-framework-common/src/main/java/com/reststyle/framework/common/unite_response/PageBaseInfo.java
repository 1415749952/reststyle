package com.reststyle.framework.common.unite_response;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:分页信息返回基类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-05
 * @Time: 10:36
 */
public class PageBaseInfo
{
    /**
     * 需要显示的数据集  每页显示的数据
     */
    @JsonView(GeneralViews.RestView.class)
    private List rows;


    /**
     * 当前页数
     */
    @JsonView(GeneralViews.RestView.class)
    private int pageNum;


    /**
     * 页面容量
     */
    @JsonView(GeneralViews.RestView.class)
    private int pageSize;


    /**
     * 总页数
     */
    @JsonView(GeneralViews.RestView.class)
    private int pages;


    /**
     * 查询到数据的条数
     */
    @JsonView(GeneralViews.RestView.class)
    private long total;


    /**
     * 默认当前页为第一页
     */
    @JsonView(GeneralViews.RestView.class)
    public final static int NO_PAGENUM = 1;

    /**
     * @param rows
     * @param page
     * @param records
     * @param size
     */

    /**
     * @param rows     查询到的集合
     * @param pageNum  当前页数
     * @param pageSize 页数据条数
     * @param total    查询到数据的条数
     */
    public PageBaseInfo(List rows, int pageNum, int pageSize, long total)
    {
        //查询到的list集合
        this.rows = rows;
        //当前页数
        this.pageNum = pageNum;
        //页面容量   一页显示的数据条数
        this.pageSize = pageSize;
        //查询到的数据条数
        this.total = total;
        //如果没有页面容量
        if (this.pageSize == 0)
        {
            this.pageSize = (int) total;
        }
        if (this.pageNum == 0)
        {
            this.pageNum = NO_PAGENUM;
        }
        //总页数
        this.pages = ((int) total + this.pageSize - 1) / this.pageSize;
    }

    public List getRows()
    {
        return rows;
    }

    public void setRows(List rows)
    {
        this.rows = rows;
    }

    public int getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getPages()
    {
        return pages;
    }

    public void setPages(int pages)
    {
        this.pages = pages;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
        //设置总记录数的时候同时设置总页数
        setPages(((int) total + this.pageSize - 1) / this.pageSize);
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    @Override
    public String toString()
    {
        return "PageBaseInfo{" +
                "rows=" + rows +
                ", pageNum=" + pageNum +
                ", pages=" + pages +
                ", total=" + total +
                ", pageSize=" + pageSize +
                '}';
    }
}