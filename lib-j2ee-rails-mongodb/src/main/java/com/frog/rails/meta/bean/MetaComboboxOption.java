package com.frog.rails.meta.bean;

import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.base.db.mongo.annotation.DBRef;
import com.cyou.fz.common.crud.property.DataBean;

/**
 * 下拉框选项.
 * @author yangz
 * @date 2014/10/4
 *
 */
@DBCollection("MetaComboboxOption")
public class MetaComboboxOption extends DataBean{
    /**
     * 名称.
     */
    private String name;
    /**
     * 分组.
     */
    private String group;

    @DBRef
    private MetaCombobox metaCombobox;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public MetaCombobox getMetaCombobox() {
        return metaCombobox;
    }

    public void setMetaCombobox(MetaCombobox metaCombobox) {
        this.metaCombobox = metaCombobox;
    }
}
