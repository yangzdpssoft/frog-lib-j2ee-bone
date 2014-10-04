package com.frog.rails.vo.easyui;

import java.util.List;

/**
 * easyUI datagrid数据包.
 * @author yangz
 * @date 2014/10/3
 *
 */

public class DatagridVO {
    /**
     * 总行数.
     */
    private Integer total = -1;
    /**
     * 当前页数据.
     */
    private List rows;
    /**
     * 统计信息.
     */
    private List footer;

    public List<Object> getRows() {
        return rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public List getFooter() {
        return footer;
    }

    public void setFooter(List footer) {
        this.footer = footer;
    }
}
