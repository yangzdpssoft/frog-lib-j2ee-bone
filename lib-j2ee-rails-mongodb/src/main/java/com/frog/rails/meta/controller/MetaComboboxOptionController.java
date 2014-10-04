package com.frog.rails.meta.controller;

import com.frog.rails.meta.dao.MetaComboboxOptionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 元数据 -- 模块.
 * @author yangz
 * @date 2014/10/3
 *
 */

@Controller
public class MetaComboboxOptionController {

    @Autowired
    private MetaComboboxOptionDAO dao;

}
