package com.cyou.fz.common.base.springmvc.expand.controller;

import com.cyou.fz.common.base.exception.UnCaughtException;
import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.springmvc.ajax.ResponseFactory;
import com.cyou.fz.common.base.util.StringUtil;
import com.cyou.fz.common.base.util.WebUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传.
 * @author yangz
 * @date 2014/8/12
 *
 */
@Controller
public class UpController {

    private String adminUpload = "/adminUpload/";

    private String frontUpload = "/frontUpload/";

    /**
     * 后台上传.
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value="/admin/up", method= RequestMethod.POST)
    @ResponseBody
    public Response<String> adminUp(@RequestParam MultipartFile file, HttpServletRequest request){
        Response<String> result = ResponseFactory.getDefaultSuccessResponse();
        String realPath = WebUtil.getWebAppRoot(request, adminUpload);
        String fileName = UUID.randomUUID().toString() + '.' + StringUtil.getFilenameExtension(file.getOriginalFilename());
        try {
            if(file != null && !file.isEmpty()){
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, fileName));
                result.setData(adminUpload  + fileName);
            }else{
                result = ResponseFactory.getDefaultFailureResponse();
                result.setData("文件未能正确上传");
            }
        } catch (IOException e) {
            throw new UnCaughtException(e);
        }
        return result;
    }

    /**
     * 前台上传.
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value="/front/up", method= RequestMethod.POST)
    @ResponseBody
    public Response<String> frontUp(@RequestParam MultipartFile file, HttpServletRequest request){
        Response<String> result = ResponseFactory.getDefaultSuccessResponse();
        String realPath = WebUtil.getWebAppRoot(request, frontUpload);
        String fileName = UUID.randomUUID().toString() + StringUtil.getFilenameExtension(file.getOriginalFilename());
        try {
            if(file != null && !file.isEmpty()){
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, fileName));
                result.setData(frontUpload  + fileName);
            }else{
                result = ResponseFactory.getDefaultFailureResponse();
                result.setData("文件未正确上传");
            }
        } catch (IOException e) {
            throw new UnCaughtException(e);
        }
        return result;
    }

    /**
     * 后端下载.
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping("/admin/download")
    @ResponseBody
    public ResponseEntity<byte[]> adminDownload(String fileName, HttpServletRequest request) {
        try {
            String fn = StringUtil.getWithoutPath(fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fn);
            File file = new File(WebUtil.getWebAppRoot(request, StringUtil.appendPath(adminUpload, fn)));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),  headers, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new UnCaughtException(e);
        }
    }

    /**
     * 前端下载.
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping("/front/download")
    @ResponseBody
    public ResponseEntity<byte[]> frontDownload(String fileName, HttpServletRequest request) {
        try {
            String fn = StringUtil.getWithoutPath(fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fn);
            File file = new File(WebUtil.getWebAppRoot(request, StringUtil.appendPath(frontUpload, fn)));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),  headers, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new UnCaughtException(e);
        }
    }
}
