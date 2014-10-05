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
     * 名称.
     */
    private String name;
    /**
     * 类型.
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
