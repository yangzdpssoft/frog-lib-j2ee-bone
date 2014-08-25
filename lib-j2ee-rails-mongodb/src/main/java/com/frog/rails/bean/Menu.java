package com.frog.rails.bean;


import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.crud.property.DataBean;

import java.util.ArrayList;
import java.util.List;

@DBCollection("Menu")
public class Menu extends DataBean{

    private String iconClass;

    private String name;

    private List<MenuItem> menuItems = new ArrayList();

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }
}
