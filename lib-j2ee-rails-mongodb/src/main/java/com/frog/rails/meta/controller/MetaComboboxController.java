package com.frog.rails.meta.controller;

import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import com.cyou.fz.common.base.util.ValueUtil;
import com.frog.rails.meta.bean.MetaCombobox;
import com.frog.rails.meta.dao.MetaComboboxDAO;
import com.frog.rails.vo.easyui.ComboVO;
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
    public Response<ComboVO> saveOrUpdate(String id, String text){
        Response<ComboVO> result = ResponseFactory.getDefaultSuccessResponse();
        MetaCombobox item = new MetaCombobox();
        item.setId(id);
        item.setName(text);
        String newId = dao.saveOrUpdate(item);
        ComboVO vo = new ComboVO();
        vo.setId(newId);
        vo.setText(text);
        result.setData(vo);
        return result;
    }

    /**
     * 删除.
     * @return
     */
    @RequestMapping("/factory/metaCombobox/delete")
    @ResponseBody
    public Response<Boolean> delete(String[] id){
        Response<Boolean> result = ResponseFactory.getDefaultSuccessResponse();
        dao.delete(id);
        return result;
    }

    /**
     * 列表.
     * @return
     */
    @RequestMapping("/factory/metaCombobox/list")
    @ResponseBody
    public List<ComboVO> list(){
        List<ComboVO> result = new ArrayList<>();
        Map<String, Object> cond = Collections.emptyMap();
        Map<String, Object> sort = Collections.emptyMap();
        List<MetaCombobox> list = dao.queryAll(cond, sort);
        for(MetaCombobox item : list){
            ComboVO vo = new ComboVO();
            vo.setId(item.getId());
            vo.setText(ValueUtil.getString(item.getName()));
            result.add(vo);
        }
        return result;
    }

}
