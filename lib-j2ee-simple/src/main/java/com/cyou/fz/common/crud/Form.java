package com.cyou.fz.common.crud;

import java.util.HashMap;
import java.util.Map;

/**
 * 表单封装器.
 * @author yangz
 * @date 2014/7/22
 *
 */
public class Form {
    /**
     * 值栈.
     */
    private Map<String, Object> value = new HashMap<String, Object>();

    public Map<String, Object> getValue() {
        return value;
    }

    public void setValue(Map<String, Object> value) {
        this.value = value;
    }
}
