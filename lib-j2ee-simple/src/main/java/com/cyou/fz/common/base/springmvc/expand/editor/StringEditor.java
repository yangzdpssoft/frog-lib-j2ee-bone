package com.cyou.fz.common.base.springmvc.expand.editor;

import com.cyou.fz.common.base.util.ObjectUtil;
import com.cyou.fz.common.base.util.StringUtil;
import com.cyou.fz.common.base.util.ValueUtil;
import org.springframework.web.util.HtmlUtils;

import java.beans.PropertyEditorSupport;


/**
 * 属性转换器.
 * @author yangz
 * @date 2013-3-30 下午3:14:08
 */
	
public class StringEditor extends PropertyEditorSupport {
	
	public void setAsText(String text) throws IllegalArgumentException {
		if(!ObjectUtil.isEmpty(text)){
			setValue(HtmlUtils.htmlEscape(StringUtil.trim(text)));
		}else{
			setValue(null);
		}
	}

	public String getAsText() {
        Object value = getValue();
        return value == null ? null : ValueUtil.getString(value);
	}
}