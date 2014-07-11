package com.cyou.fz.common.base.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;

import com.cyou.fz.common.base.exception.UnCaughtException;
import com.cyou.fz.common.base.util.ClassUtil;



/**
 * 初始化静态常量.
 * @author yangz
 * @date 2013-6-1 下午11:51:36
 */
public class InitStaticConstantField implements InitializingBean{
	
	private Map<String, String> valueMappings = new HashMap<String, String>();

	/**
	 * @return Returns the valueMappings.
	 * @author yangz
	 * @date   2013-6-2 上午12:03:22
	 */
	public Map<String, String> getValueMappings() {
		return valueMappings;
	}

	/**
	 * @param  valueMappings The valueMappings to set.
	 * @author yangz	
	 * @date   2013-6-2 上午12:03:22
	 */
	public void setValueMappings(Map<String, String> valueMappings) {
		this.valueMappings = valueMappings;
	}

	/**
	 * @throws Exception
	 * @author yangz
	 * @date 2013-6-2 上午12:26:15
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			Set<Entry<String, String>> entries = valueMappings.entrySet();
			for (Entry<String, String> entry : entries) {
				ClassUtil.setStaticConstantField(Class.forName(entry.getKey()), entry.getValue());
			}
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
    }
	
	
}
