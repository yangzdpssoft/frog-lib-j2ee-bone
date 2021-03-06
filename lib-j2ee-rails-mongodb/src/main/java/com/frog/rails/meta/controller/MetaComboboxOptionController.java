package com.frog.rails.meta.controller;

import com.cyou.fz.common.base.constant.CondPrefixConstant;
import com.cyou.fz.common.base.constant.FieldConstant;
import com.cyou.fz.common.base.exception.UnCaughtException;
import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import com.cyou.fz.common.base.util.CollectionUtil;
import com.cyou.fz.common.base.util.JsonUtil;
import com.cyou.fz.common.base.util.ObjectUtil;
import com.frog.rails.meta.bean.MetaCombobox;
import com.frog.rails.meta.bean.MetaComboboxOption;
import com.frog.rails.meta.dao.MetaComboboxDAO;
import com.frog.rails.meta.dao.MetaComboboxOptionDAO;
import com.frog.rails.vo.easyui.ComboVO;
import com.frog.rails.vo.easyui.DatagridVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
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

    private final static String COND_COMBOBOX = CondPrefixConstant.COND_PREFIX + MetaComboboxOption.META_COMBOBOX + "." + FieldConstant.ID;

    @Autowired
    private MetaComboboxOptionDAO dao;
    @Autowired
    private MetaComboboxDAO metaComboboxDAO;
    /**
     * 保存.
     * @return
     */
    @RequestMapping("/factory/metaComboboxOption/saveOrUpdate")
    @ResponseBody
    public Response<String> saveOrUpdate(String dependencyId, String jsonValue){
        List<MetaComboboxOption> list = JsonUtil.toObject(HtmlUtils.htmlUnescape(jsonValue), JsonUtil.getCollectionType(ArrayList.class, MetaComboboxOption.class));
        MetaCombobox combobox = metaComboboxDAO.get(dependencyId);
        metaComboboxDAO.saveOrUpdate(combobox);
        for (MetaComboboxOption item : list){
            item.setMetaCombobox(combobox);
            dao.saveOrUpdate(item);
        }
        return ResponseFactory.getDefaultSuccessResponse();
    }

    /**
     * 删除.
     * @return
     */
    @RequestMapping("/factory/metaComboboxOption/delete")
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
    @RequestMapping("/factory/metaComboboxOption/list")
    @ResponseBody
    public DatagridVO list(String dependencyId){
        DatagridVO result = new DatagridVO();
        if(!ObjectUtil.isEmpty(dependencyId)){
            Map<String, Object> cond = CollectionUtil.emptyMap();
            Map<String, Object> sort = CollectionUtil.emptyMap();
            cond.put(COND_COMBOBOX, dependencyId);
            List<MetaComboboxOption> list = dao.queryAll(cond, sort);
            result.setRows(list);
        }else{
            result.setRows(CollectionUtil.emptyList());
        }
        return result;
    }
    /**
     * 选项.
     * @return
     */
    @RequestMapping("/factory/metaComboboxOption/options")
    @ResponseBody
    public List<ComboVO> options(String name){
        if(!ObjectUtil.isEmpty(name)){
            List<ComboVO> result = new ArrayList<>();
            MetaCombobox box = metaComboboxDAO.unique(MetaCombobox.NAME, name);
            Map<String, Object> cond = CollectionUtil.emptyMap();
            Map<String, Object> sort = CollectionUtil.emptyMap();
            cond.put(COND_COMBOBOX, box.getId());
            List<MetaComboboxOption> list = dao.queryAll(cond, sort);
            for(MetaComboboxOption option : list){
                ComboVO vo = new ComboVO();
                vo.setId(option.getId());
                vo.setText(option.getName());
                result.add(vo);
            }
            return result;
        }else{
            throw new UnCaughtException("非法请求");
        }
    }
}
