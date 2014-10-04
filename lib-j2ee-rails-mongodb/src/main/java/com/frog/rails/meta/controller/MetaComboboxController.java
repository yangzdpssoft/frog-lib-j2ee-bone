package com.frog.rails.meta.controller;

import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.frog.rails.meta.bean.MetaCombobox;
import com.frog.rails.meta.dao.MetaComboboxDAO;
import com.frog.rails.vo.easyui.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class MetaComboboxController {

    @Autowired
    private MetaComboboxDAO dao;

    /**
     * 保存.
     * @return
     */
    @RequestMapping("/factory/metaCombobox/saveOrUpdate")
    @ResponseBody
    public Response<Boolean> saveOrUpdate(String id, String text){
        return null;
    }

    /**
     * 列表.
     * @return
     */
    @RequestMapping("/factory/metaCombobox/list")
    @ResponseBody
    public List<TreeVO> list(){
        List<TreeVO> result = new ArrayList<>();
        Map<String, Object> cond = Collections.emptyMap();
        Map<String, Object> sort = Collections.emptyMap();
        List<MetaCombobox> list = dao.queryAll(cond, sort);
        for(MetaCombobox item : list){
            TreeVO vo = new TreeVO();
            vo.setId(item.getId());
            vo.setText(item.getName());
            result.add(vo);
        }
        return result;
    }

}
