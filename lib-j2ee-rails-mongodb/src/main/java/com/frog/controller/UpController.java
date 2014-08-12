package com.frog.controller;

import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传.
 * @author yangz
 * @date 2014/8/12
 *
 */
@Controller
public class UpController {

    @RequestMapping(value="/admin/up", method= RequestMethod.POST)
    @ResponseBody
    public Response<Boolean> up(@RequestParam MultipartFile[] file, HttpServletRequest request){
        Response<Boolean> result = ResponseFactory.getDefaultSuccessResponse();
        return result;
    }
}
