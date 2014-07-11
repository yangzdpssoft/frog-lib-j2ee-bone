package com.cyou.fz.common.base.springmvc.expand.editor;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.math.BigDecimal;
/**
 * 属性转换器初始化.
 * 
 * @author yangz
 * @date 2013-5-31 下午2:10:01
 */
public class BindingInitializer implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(String.class, new StringEditor());
		binder.registerCustomEditor(Long.class, new LongEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(Integer.class, new IntegerEditor());
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(Double.class, new DoubleEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
	}
	
}
