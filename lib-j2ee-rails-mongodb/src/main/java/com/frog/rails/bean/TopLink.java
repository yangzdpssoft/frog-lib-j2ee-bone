package com.frog.rails.bean;


import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.crud.property.DataBean;

@DBCollection("TopLink")
public class TopLink extends DataBean {

    private String name;

    private String url;

    private String iconClass;

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
