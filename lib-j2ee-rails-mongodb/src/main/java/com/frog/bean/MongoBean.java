package com.frog.bean;

import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.crud.property.DataBean;

/**
 *
 * @author yangz
 * @date 2014/7/24
 *
 */

@DBCollection("MongoBean")
public class MongoBean extends DataBean{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
