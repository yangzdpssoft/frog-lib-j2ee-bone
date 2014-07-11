package com.cyou.fz.common.base.springmvc.expand;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * spring mvc 默认地址匹配.
 * 
 * @author yangz
 * @date 2013-6-1 下午4:35:02
 */
public class WildcardMVCController {
	/**
	 * 默认匹配所有地址.
	 * 
	 * @return
	 * @author yangz
	 * @date 2013-4-15 下午2:16:00
	 */
	@RequestMapping("/**/*")
	public ModelAndView htmlMapping() {
		ModelAndView view = new ModelAndView();
		return view;
	}
}
