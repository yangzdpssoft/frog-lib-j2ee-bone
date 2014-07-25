package com.frog.bean;

import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.crud.property.DataBean;

@DBCollection("SubBean")
public class SubBean extends DataBean{

    private String subName;

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
