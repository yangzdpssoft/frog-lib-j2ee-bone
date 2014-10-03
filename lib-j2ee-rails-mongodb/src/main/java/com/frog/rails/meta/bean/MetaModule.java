package com.frog.rails.meta.bean;

import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.crud.property.DataBean;

/**
 * 元数据模块.
 * @author yangz
 * @date 2014/10/3
 *
 */
@DBCollection("MetaModule")
public class MetaModule extends DataBean {
    /**
     * 模块名.
     */
    private String name;
    /**
     * 表名.
     */
    private String tableName;
    /**
     * 管理类型.
     */
    private Integer manageType;

    public Integer getManageType() {
        return manageType;
    }

    public void setManageType(Integer manageType) {
        this.manageType = manageType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
