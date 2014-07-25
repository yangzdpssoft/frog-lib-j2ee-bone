package com.frog.bean;

import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.base.db.mongo.annotation.DBRef;
import com.cyou.fz.common.crud.property.DataBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yangz
 * @date 2014/7/24
 *
 */

@DBCollection("MongoBean")
public class MongoBean extends DataBean{

    private String name;

    private long inc;

    @DBRef
    private List<SubBean> subBeans = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubBean> getSubBeans() {
        return subBeans;
    }

    public void setSubBeans(List<SubBean> subBeans) {
        this.subBeans = subBeans;
    }

    public long getInc() {
        return inc;
    }

    public void setInc(long inc) {
        this.inc = inc;
    }
}
