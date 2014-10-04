package com.frog.rails.meta.controller;

import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import com.cyou.fz.common.base.util.JsonUtil;
import com.frog.rails.meta.bean.MetaComboboxOption;
import com.frog.rails.meta.dao.MetaComboboxOptionDAO;
import com.frog.rails.vo.easyui.DatagridVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    /**
     * 保存.
     * @return
     */
    @RequestMapping("/factory/metaComboboxOption/saveOrUpdate")
    @ResponseBody
    public Response<MetaComboboxOption> saveOrUpdate(String comboboxId, String jsonValue){
        List<MetaComboboxOption> list = JsonUtil.toObject(HtmlUtils.htmlUnescape(jsonValue), JsonUtil.getCollectionType(ArrayList.class, MetaComboboxOption.class));
//        for (MetaModule item : list){
//            dao.saveOrUpdate(item);
//        }
        return ResponseFactory.getDefaultSuccessResponse();
    }

    /**
     * 列表数据.
     * @return
     */
    @RequestMapping("/factory/metaComboboxOption/list")
    @ResponseBody
    public DatagridVO list(){
        DatagridVO result = new DatagridVO();
        Map<String, Object> cond = Collections.emptyMap();
        Map<String, Object> sort = Collections.emptyMap();
        List<MetaComboboxOption> list = dao.queryAll(cond, sort);
        result.setRows(list);
        return result;
    }
}
