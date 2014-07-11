package com.cyou.fz.common.base.util;

import com.cyou.fz.common.base.exception.UnCaughtException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;

/**
 * 类工具.
 * 
 * @author yangz
 * @date 2013-1-31 下午3:23:11
 */
public class ClassUtil {
	/**
	 * 是否是值类型.
	 * 
	 * @param clz
	 * @return
	 * @author yangz
	 * @date 2012-9-26 下午03:17:09
	 */
	public static boolean isValueType(Class<?> clz) {
		if (clz != null) {
			if (clz.isPrimitive() || Number.class.isAssignableFrom(clz) || Character.class.isAssignableFrom(clz) || String.class.isAssignableFrom(clz) || Date.class.isAssignableFrom(clz)
					|| Boolean.class.isAssignableFrom(clz)) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new UnCaughtException("Class can't be null");
		}
	}

	/**
	 * 实例化对象.
	 * 
	 * @param className
	 * @return
	 * @author yangz
	 * @date 2012-7-29 上午10:55:03
	 */
	public static Object newInstance(String className) {
		try {
			return newInstance(Class.forName(className));
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 实例化对象.
	 * 
	 * @param c
	 * @return
	 * @author yangz
	 * @date 2012-7-29 上午10:57:08
	 */
	@SuppressWarnings("rawtypes")
	public static Object newInstance(Class c) {
		try {
			return c.newInstance();
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 获取字段, 找不到字段抛异常.
	 * 
	 * @param clz
	 * @param fieldName
	 * @return
	 * @author yangz
	 * @date 2012-9-27 下午04:08:47
	 */
	@SuppressWarnings("rawtypes")
	public static Field getField(Class clz, String fieldName) {
		return getField(clz, fieldName, true);
	}

	/**
	 * 获取字段, exception=false时,找不到字段可返回null.
	 * 
	 * @param clz
	 * @param fieldName
	 * @return
	 * @author yangz
	 * @date 2012-9-27 下午04:08:47
	 */
	@SuppressWarnings("rawtypes")
	public static Field getField(Class clz, String fieldName, boolean exception) {
		if (clz != null) {
			while (true) {
				try {
					if (!Object.class.getName().equals(clz.getName())) {
						return clz.getDeclaredField(fieldName);
					} else {
						if (exception) {
							throw new UnCaughtException("no such field in " + clz.getName());
						} else {
							return null;
						}
					}
				} catch (NoSuchFieldException e) {
					clz = clz.getSuperclass();
				}
			}
		} else {
			throw new UnCaughtException("Class can't be null");
		}
	}

	/**
	 * 取泛型类型.
	 * 
	 * @param type
	 * @return
	 * @author yangz
	 * @date 2012-9-27 下午04:28:40
	 */
	@SuppressWarnings("rawtypes")
	public static Type[] getActualTypes(Type type) {
		if (type instanceof ParameterizedType) {
			ParameterizedType t = (ParameterizedType) type;
			Type[] types = t.getActualTypeArguments();
			if (ObjectUtil.isEmpty(types)) {
				throw new UnCaughtException(((Class) type).getName() + " not have ActualType.");
			}
			return types;
		} else if (type instanceof Class) {
			Type[] types = null;
			if (isCGLibProxy((Class) type)) {// 是否是CGLib代理对象
				Class proxyType = (Class) ((Class) type).getGenericSuperclass(); // 代理类
				types = ((ParameterizedType) proxyType.getGenericSuperclass()).getActualTypeArguments();
			} else {
				types = ((ParameterizedType) ((Class) type).getGenericSuperclass()).getActualTypeArguments();
			}
			if (ObjectUtil.isEmpty(types)) {
				throw new UnCaughtException(((Class) type).getName() + " not have ActualType.");
			}
			return types;
		} else {
			throw new UnCaughtException("type error.");
		}
	}

	/**
	 * 是否是cglib代理类.
	 * 
	 * @param type
	 * @return
	 * @author yangz
	 * @date 2013-1-5 下午02:10:31
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isCGLibProxy(Class type) {
		// FIXME 这种方法可行, 但是比较土
		return type.getName().contains("EnhancerByCGLIB") || type.getName().contains("EnhancerBySpringCGLIB");
	}

	/**
	 * 取泛型类型.
	 * 
	 * @param type
	 * @return
	 * @author yangz
	 * @date 2012-9-27 下午04:28:40
	 */
	@SuppressWarnings("rawtypes")
	public static Class getActualType(Type type) {
		Type[] types = getActualTypes(type);
		if (types != null && types.length > 0) {
			return (Class) types[0];
		} else {
			throw new UnCaughtException(((Class) type).getName() + "not have actual type.");
		}
	}

	/**
	 * 获取类的属性描述.
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static BeanInfo getBeanInfo(Class type) {
		try {
			return Introspector.getBeanInfo(type);
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 是否有指定属性.
	 * 
	 * @param clz
	 * @param propertyName
	 * @return
	 * @author yangz
	 * @date 2012-9-29 上午11:21:59
	 */
	@SuppressWarnings("rawtypes")
	public static boolean hasProperty(Class clz, String propertyName) {
		PropertyDescriptor property = getProperty(clz, propertyName);
		return property != null;
	}

	/**
	 * 获取属性字段.
	 * 
	 * @param beanInfo
	 * @param propertyName
	 * @return
	 * @author yangz
	 * @date 2012-10-12 下午04:50:08
	 */
	public static PropertyDescriptor getProperty(BeanInfo beanInfo, String propertyName) {
		PropertyDescriptor[] propertys = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertys) {
			if (propertyDescriptor.getName().equals(propertyName) && !Object.class.getName().equals(propertyName)) {
				return propertyDescriptor;
			}
		}
		return null;
	}

	/**
	 * 获取属性字段.
	 * 
	 * @param beanClass
	 * @param propertyName
	 * @return
	 * @author yangz
	 * @date 2012-10-12 下午04:52:20
	 */
	@SuppressWarnings("rawtypes")
	public static PropertyDescriptor getProperty(Class beanClass, String propertyName) {
		try {
			return getProperty(Introspector.getBeanInfo(beanClass), propertyName);
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 获取满足注解的属性.
	 * 
	 * @param beanClass
	 * @param annotationClasses
	 * @return
	 * @author yangz
	 * @date 2013-6-5 上午11:56:12
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<PropertyDescriptor> getProperties(Class beanClass, Class... annotationClasses) {
		try {
			List<PropertyDescriptor> result = new ArrayList<PropertyDescriptor>();
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (ObjectUtil.isEmpty(annotationClasses)) {
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					String propertyName = propertyDescriptor.getName();
					if (!"class".equals(propertyName)) {
						result.add(propertyDescriptor);
					}
				}
			} else {
				for (Class annotationClass : annotationClasses) {
					for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
						String propertyName = propertyDescriptor.getName();
						if (!"class".equals(propertyName)) {
							Field field = getField(beanClass, propertyName);
							Annotation annotation = field.getAnnotation(annotationClass);
							if (annotation != null) {
								result.add(propertyDescriptor);
							}
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 获取属性类型
	 * 
	 * @param beanInfo
	 * @param propertyName
	 * @return
	 * @author yangz
	 * @date 2012-10-11 下午06:32:14
	 */
	@SuppressWarnings("rawtypes")
	public static Class getPropertyType(BeanInfo beanInfo, String propertyName) {
		return getProperty(beanInfo, propertyName).getPropertyType();
	}

	/**
	 * 获取属性类型
	 * 
	 * @param beanClass
	 * @param propertyName
	 * @return
	 * @author yangz
	 * @date 2013-2-8 上午11:57:30
	 */
	@SuppressWarnings("rawtypes")
	public static Class getPropertyType(Class beanClass, String propertyName) {
		try {
			return getProperty(Introspector.getBeanInfo(beanClass), propertyName).getPropertyType();
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 读取属性值.
	 * 
	 * @param obj
	 * @param propertyName
	 * @return
	 * @author yangz
	 * @date 2012-10-12 下午04:55:14
	 */
	public static Object getPropertyValue(Object obj, String propertyName) {
		try {
			return getProperty(obj.getClass(), propertyName).getReadMethod().invoke(obj);
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 设置属性值.
	 * 
	 * @param obj
	 * @param propertyName
	 * @param propertyValue
	 * @author yangz
	 * @date 2013-4-29 上午10:22:07
	 */
	public static void setProperty(Object obj, String propertyName, Object propertyValue) {
		try {
			getProperty(obj.getClass(), propertyName).getWriteMethod().invoke(obj, propertyValue);
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 根据配置文件设置非final静态变量.
	 * 
	 * @param constantClass
	 * @param propertyFileName
	 * @author yangz
	 * @date 2013-5-29 下午9:41:18
	 */
	public static void setStaticConstantField(Class<?> constantClass, String propertyFileName) {
		InputStream inputStream = null;
		try {
			inputStream = IOUtil.getClassPathInputStream(propertyFileName);
			if (inputStream != null) {
				Properties init = new Properties();
				init.load(inputStream);
				Set<Entry<Object, Object>> keys = init.entrySet();
				for (Entry<Object, Object> entry : keys) {
					String key = (String) entry.getKey();
					Field field = ClassUtil.getField(constantClass, key, false);
					if (field != null) {
						field.setAccessible(true);
						field.set(null, ValueUtil.castValue(entry.getValue(), field.getType()));
						field.setAccessible(false);
					}
				}
			} else {
				throw new UnCaughtException("not exists properties file : " + propertyFileName);
			}
		} catch (Exception e) {
			throw new UnCaughtException(e);
		} finally {
			IOUtil.close(inputStream);
		}
	}

	/**
	 * 方法调用.
	 * 
	 * @param target
	 * @param args
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static Object invoke(Object target, Object[] args, String methodName, Class<?>... parameterTypes) {
		try {
			return target.getClass().getMethod(methodName, parameterTypes).invoke(target, args);
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 是否存在注解.
	 * 
	 * @param target
	 * @param annotationClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean hasAnnotation(Object target, Class annotationClass) {
		Object value = invoke(target, new Object[] { annotationClass }, "getAnnotation", Class.class);
		return value != null ? true : false;
	}
}
