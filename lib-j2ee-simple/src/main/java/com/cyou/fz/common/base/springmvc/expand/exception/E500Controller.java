/**
 * 北京畅游时空软件技术有限公司福州分公司 - 版权所有
 * 2013-5-14 上午9:12:06
 */
package com.cyou.fz.common.base.springmvc.expand.exception;

import com.cyou.fz.common.base.util.ValueUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class E500Controller {

    /**
     * 后台请求地址.
     */
    private String adminUrl = "admin/";
    /**
     * 前台请求地址.
     */
    private String frontUrl = "front/";
    /**
     * 错误代码.
     */
    private String errorCode = "500";

    /**
     * 日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(E500Controller.class);

    /**
     * 处理发生错误时的跳转.
     *
     * @param request 请求
     * @return 页面地址
     * @author yangz 2013 2013-5-14 上午11:11:42
     */
    @RequestMapping("/500")
    public ModelAndView errorHandle(HttpServletRequest request) {
        ModelAndView result = new ModelAndView();
        Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
        String status = ValueUtil.getString(request.getAttribute("javax.servlet.error.status_code"));
        if (e != null) {
            // 异常原因
            Throwable t = e.getCause();
            if (t != null) {
                if (t.getMessage().contains(adminUrl)) { //后台
                    result.setViewName(adminUrl + errorCode);
                } else {
                    result.setViewName(frontUrl + errorCode); //前台
                }
                LoggerFactory.getLogger(this.getClass()).error(status, t);
            } else {
                if (e.getMessage().contains(adminUrl)) { //后台
                    result.setViewName(adminUrl + errorCode);
                } else {
                    result.setViewName(frontUrl + errorCode); //前台
                }
                LoggerFactory.getLogger(this.getClass()).error(status, e);
            }
        }
        return result;
    }

}
