package com.frog.rails.meta.controller;

import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import com.cyou.fz.common.crud.Form;
import com.frog.rails.meta.bean.MetaModule;
import com.frog.rails.vo.easyui.Datagrid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 元数据 -- 模块.
 * @author yangz
 * @date 2014/10/3
 *
 */

@Controller
public class MetaModuleController {
    /**
     * 保存.
     * @param metaModuleList
     * @return
     */
    @RequestMapping("/factory/metaModule/save")
    @ResponseBody
    public Response<Boolean> save(List<MetaModule> metaModuleList){
        return ResponseFactory.getDefaultSuccessResponse();
    }

    /**
     * 删除.
     * @param form
     * @return
     */
    @RequestMapping("/factory/metaModule/delete")
    @ResponseBody
    public Response<Boolean> delete(Form form){
        return ResponseFactory.getDefaultSuccessResponse();
    }

    /**
     * 列表数据.
     * @return
     */
    @RequestMapping("/factory/metaModule/list")
    @ResponseBody
    public Datagrid list(){
        Datagrid result = new Datagrid();

        return result;
    }

}
