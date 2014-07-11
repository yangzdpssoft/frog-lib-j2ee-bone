package com.cyou.fz.common.base.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * spring 容器.
 * 
 * @author yangz
 * @date 2013-5-31 下午4:51:40
 */
public class SpringContext implements ApplicationContextAware {
	private static Logger logger = LoggerFactory
			.getLogger(SpringContext.class);

	private static ApplicationContext context = null;

	public static ApplicationContext getContext() {
		if (context == null) {
			logger.error("当前context为空,可能是Spring配置文件中没有配置加载本类[{}]!",
					SpringContext.class.getName());
			throw new IllegalStateException(
					"当前没有Spring的applicationContext注入,请确定是否有配置Spring,并在Spring中配置了本类的注入!"
							+ SpringContext.class);
		}
		return context;
	}

	@SuppressWarnings("unchecked")
	public static <E> E getBeanByType(Class<? extends E> type) {
		try {
			String[] beanNames = getContext().getBeanNamesForType(type);
			if (beanNames != null && beanNames.length == 1) {
				return (E) getContext().getBean(beanNames[0]);
			}

			if (beanNames == null || beanNames.length == 0) {
				throw new IllegalStateException("未找到指定类型的Bean定义.");
			}

			throw new IllegalStateException("找到多个同类型的Bean定义.");

		} catch (Throwable th) {
			logger.error("根据类型在Spring上下文查找对象出错:" + type, th);
			throw new IllegalStateException("根据类型在Spring上下文查找对象出错:" + type);
		}
	}

	@SuppressWarnings("unchecked")
	// 从Spring中取对象并转换是免不了有这错误的,所以忽略
	public static <E> E getBean(String beanName) {
		try {
			return (E) getContext().getBean(beanName);
		} catch (Throwable th) {
			// logger.error("在Spring上下文查找对象出错:" + beanName, th);
			throw new IllegalStateException("在Spring上下文查找对象出错:" + beanName);
		}
	}

	public static <E> E getBean(Class<E> clazz) {
		return getBeanByType(clazz);
		// return getBean(clazz.getName());
	}

	public static boolean containBean(String beanName) {
		return getContext().containsBean(beanName);
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		logger.debug("准备注入SpringContext[{}]", context.toString());
//		if (SpringContext.context != null) {
//			logger.warn("注意,已经注入过Spring上下文[{}],请检查配置是否有问题导致重复注入!",
//					SpringContext.context.toString());
//		}
		SpringContext.context = context;
	}

}
