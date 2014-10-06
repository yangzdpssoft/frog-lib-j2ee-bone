package com.cyou.fz.common.crud.property;

import java.io.Serializable;

/**
 * 约定所有的数据均有主键.
 * @author yangz
 * @date 2013-10-11 上午11:32
 */
public class DataBean implements IId, Serializable{
    /**
     * 主键.
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
