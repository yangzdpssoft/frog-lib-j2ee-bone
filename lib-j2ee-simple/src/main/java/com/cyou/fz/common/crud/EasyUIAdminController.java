package com.cyou.fz.common.crud;


import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.vo.PaginationVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class EasyUIAdminController<T> {

    /**
     * 保存或更新.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/save")
    @ResponseBody
    public Response<T> save(@PathVariable String domain, T t){
        return null;
    }

    /**
     * 更新.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/update")
    @ResponseBody
    public Response<T> update(@PathVariable String domain, Form form){
        return null;
    }

    /**
     * 删除.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/delete/{id}")
    @ResponseBody
    public Response<Boolean> delete(@PathVariable String domain, @PathVariable String id){
        return null;
    }

    /**
     * 查看详情.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/get/{id}")
    @ResponseBody
    public Response<T> get(@PathVariable String domain, @PathVariable String id){
        return null;
    }

    /**
     * 列表.
     * @param domain
     * @return
     */
    @RequestMapping("/admin/{domain}/list")
    @ResponseBody
    public Response<PaginationVO<T>> list(@PathVariable String domain, Form form){
        return null;
    }
}
