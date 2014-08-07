package com.cyou.fz.common.base.springmvc.expand;

import com.cyou.fz.common.base.util.WebUtil;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * freemarker视图.
 * 
 * @author yangz
 * @date 2013-5-31 上午10:00:19
 */
public class WebFreeMarkerView extends FreeMarkerView {

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        String hostPath = WebUtil.getHostPath(request);
        model.put("ctx", request.getContextPath());
        model.put("hostPath", hostPath);
        if(!"".equals(request.getContextPath())){
            model.put("defaultjs", (request.getContextPath() + "/template/" + request.getRequestURI().replace(request.getContextPath() + "/", "") + ".js").replace(".html.js", ".js"));
        }else{
            model.put("defaultjs", ("/template/" + request.getRequestURI().substring(1) + ".js").replace(".html.js", ".js"));
        }
    }
	
}
