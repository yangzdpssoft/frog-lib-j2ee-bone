/**
 * 北京畅游时空软件技术有限公司福州分公司 - 版权所有
 * 2013-5-14 上午9:12:06
 */
package com.cyou.fz.common.base.springmvc.expand.exception;

import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import com.cyou.fz.common.base.util.JvmUtil;
import com.cyou.fz.common.base.util.ValueUtil;
import com.cyou.fz.common.base.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class E403Controller {

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
    private String errorCode = "403";

    /**
     * 日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(E403Controller.class);

    /**
     * 处理发生错误时的跳转.
     *
     * @param request 请求
     * @return 页面地址
     * @author yangz 2013 2013-5-14 上午11:11:42
     */
    @RequestMapping("/403")
    public ModelAndView errorHandle(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView result = new ModelAndView();
        Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
        String uri = ValueUtil.getString(request.getAttribute("javax.servlet.error.request_uri"));
        String status = ValueUtil.getString(request.getAttribute("javax.servlet.error.status_code"));
        String errorLocation = status + " : " + uri;
        if (uri.contains(adminUrl)) { //后台
            result.setViewName(adminUrl + errorCode);
        } else if(uri.contains(frontUrl)){
            result.setViewName(frontUrl + errorCode); //前台
        }else{
            result.setViewName(errorCode);
        }
        if(e != null){
            if(WebUtil.isAjaxRequest(request)){
                Response<String> ajaxResponse = ResponseFactory.getDefaultErrorResponse(e.toString());
                WebUtil.responseJson(response, ajaxResponse);
            }else {
                String errorMessage = JvmUtil.printHTMLStackTrace(e);
                request.setAttribute("errorUri", "HTTP | " + errorLocation);
                request.setAttribute("errorMessage", errorMessage);
            }
            LoggerFactory.getLogger(this.getClass()).error(errorLocation, e);
        }
        return result;
    }

}
