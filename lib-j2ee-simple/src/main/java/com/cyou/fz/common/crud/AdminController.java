package com.cyou.fz.common.crud;

import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 后台管理Controller.
 *
 * @author yangz
 * @date 2014/7/22
 *
 */
public class AdminController {

    /**
     * 保存或更新.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/save")
    public ModelAndView save(@PathVariable String domain, Form form){
        ModelAndView result = new ModelAndView();
        return result;
    }

    /**
     * 更新.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/update")
    public ModelAndView update(@PathVariable String domain, Form form){
        ModelAndView result = new ModelAndView();
        return result;
    }

    /**
     * 删除.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/delete/{id}")
    public ModelAndView delete(@PathVariable String domain, @PathVariable String id){
        ModelAndView result = new ModelAndView();
        return result;
    }

    /**
     * 查看详情.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/get/{id}")
    public ModelAndView get(@PathVariable String domain, @PathVariable String id){
        ModelAndView result = new ModelAndView();
        return result;
    }

    /**
     * 列表.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/list")
    public ModelAndView list(@PathVariable String domain, Form form){
        ModelAndView result = new ModelAndView();
        return result;
    }

    /**
     * ajax保存或更新.
     * @return
     */
    @RequestMapping("/admin/{domain}/save/ajax")
    @ResponseBody
    public Response<Map<String, Object>> saveAjax(@PathVariable String domain, Form form){
        Response<Map<String, Object>> response = ResponseFactory.getDefaultSuccessResponse();
        return response;
    }

    /**
     * ajax更新.
     * @return
     */
    @RequestMapping("/admin/{domain}/update/ajax")
    @ResponseBody
    public Response<Map<String, Object>> updateAjax(@PathVariable String domain, Form form){
        Response<Map<String, Object>> response = ResponseFactory.getDefaultSuccessResponse();
        return response;
    }

    /**
     * ajax删除.
     * @return
     */
    @RequestMapping("/admin/{domain}/delete/{id}/ajax")
    @ResponseBody
    public Response<Object> deleteAjax(@PathVariable String domain, @PathVariable String id){
        Response<Object> response = ResponseFactory.getDefaultSuccessResponse();
        return response;
    }

    /**
     * ajax查看详情.
     * @return
     */
    @RequestMapping("/admin/{domain}/get/{id}/ajax")
    @ResponseBody
    public Response<Object> getAjax(@PathVariable String domain, @PathVariable String id){
        Response<Object> response = ResponseFactory.getDefaultSuccessResponse();
        return response;
    }

    /**
     * ajax列表.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/list/ajax")
    public Response<List<Map<String, Object>>> listAjax(@PathVariable String domain, Form form){
        Response<List<Map<String, Object>>> response = ResponseFactory.getDefaultSuccessResponse();
        return response;
    }

}
