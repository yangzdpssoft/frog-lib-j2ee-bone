package com.cyou.fz.common.base.springmvc.expand;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.cyou.fz.common.base.util.ObjectUtil;
import com.cyou.fz.common.base.util.StringUtil;
import com.cyou.fz.common.base.util.WebUtil;


/**
 * freemarker视图.
 * 
 * @author yangz
 * @date 2013-5-31 上午10:00:19
 */
public class WebFreeMarkerView extends FreeMarkerView {

    public static String CDN_PATH;

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        String hostPath = WebUtil.getHostPath(request);
        model.put("ctx", request.getContextPath());
        model.put("hostPath", hostPath);
        if (ObjectUtil.isEmpty(CDN_PATH)){
            model.put("cdnPath", StringUtil.appendPath(hostPath, "/cdn"));
        }else{
            model.put("cdnPath", CDN_PATH);
        }
	}
	
}
