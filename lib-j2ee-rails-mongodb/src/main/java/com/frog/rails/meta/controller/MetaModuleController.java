package com.frog.rails.meta.controller;

import com.cyou.fz.common.base.exception.UnCaughtException;
import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import com.cyou.fz.common.base.util.JsonUtil;
import com.cyou.fz.common.base.util.ObjectUtil;
import com.frog.rails.meta.bean.MetaModule;
import com.frog.rails.meta.dao.MetaModuleDAO;
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
public class MetaModuleController {

    @Autowired
    private MetaModuleDAO dao;
    /**
     * 保存.
     * @return
     */
    @RequestMapping("/factory/metaModule/saveOrUpdate")
    @ResponseBody
    public Response<String> saveOrUpdate(String jsonValue){
        List<Map<String, Object>> mapList = JsonUtil.toObject(HtmlUtils.htmlUnescape(jsonValue), JsonUtil.getCollectionType(ArrayList.class, Map.class));
//        List<MetaModule> list = JsonUtil.toObject(HtmlUtils.htmlUnescape(jsonValue), JsonUtil.getCollectionType(ArrayList.class, MetaModule.class));
//        for (MetaModule item : list){
//            dao.saveOrUpdate(item);
//        }
        return ResponseFactory.getDefaultSuccessResponse();
    }

    /**
     * 删除.
     * @param id
     * @return
     */
    @RequestMapping("/factory/metaModule/delete")
    @ResponseBody
    public Response<String> delete(String id){
        if(!ObjectUtil.isEmpty(id)){
            Response<String> result = ResponseFactory.getDefaultSuccessResponse();
            String[] ids = id.split(",");
            dao.delete(ids);
            return result;
        }else{
            throw new UnCaughtException("非法请求");
        }
    }

    /**
     * 列表数据.
     * @return
     */
    @RequestMapping("/factory/metaModule/list")
    @ResponseBody
    public DatagridVO list(){
        DatagridVO result = new DatagridVO();
        Map<String, Object> cond = Collections.emptyMap();
        Map<String, Object> sort = Collections.emptyMap();
        List<MetaModule> list = dao.queryAll(cond, sort);
        result.setRows(list);
        return result;
    }

}
