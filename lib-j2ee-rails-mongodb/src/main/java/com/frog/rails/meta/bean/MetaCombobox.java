package com.frog.rails.meta.bean;


import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.base.db.mongo.annotation.DBRef;
import com.cyou.fz.common.crud.property.DataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉框.
 * @author yangz
 * @date 2014/10/4
 *
 */
@DBCollection("MetaCombobox")
public class MetaCombobox extends DataBean {

    /**
     * 名称.
     */
    private String name;

    /**
     * 选项.
     */
    @DBRef
    private List<MetaComboboxOption> options = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MetaComboboxOption> getOptions() {
        return options;
    }

    public void setOptions(List<MetaComboboxOption> options) {
        this.options = options;
    }
}
