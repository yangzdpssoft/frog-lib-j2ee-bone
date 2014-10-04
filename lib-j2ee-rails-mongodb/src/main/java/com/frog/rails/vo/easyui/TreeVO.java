package com.frog.rails.vo.easyui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * easyui 树.
 * @author yangz
 * @date 2014/10/4
 *
 */
public class TreeVO {

    private String id;

    private String text;

    private String iconCls;

    private boolean checked = false;

    private String state;

    private Map<String, Object> attributes = new HashMap<>();

    private List<TreeVO> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<TreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVO> children) {
        this.children = children;
    }

}
