package com.cyou.fz.common.base.util;

import com.cyou.fz.common.base.annotation.UpdateOnlyNotEmpty;
import com.cyou.fz.common.base.exception.UnCaughtException;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 对象工具
 * 
 * @author yangz
 * @date 2013-1-31 下午3:25:30
 */
public class ObjectUtil {
	/**
	 * One of the following conditions isEmpty = true, else = false :
	 * 满足下列一个条件则为空<br>
	 * 1. null : 空<br>
	 * 2. "" or " " : 空串<br>
	 * 3. no item in [] or all item in [] are null : 数组中没有元素, 数组中所有元素为空<br>
	 * 4. no item in (Collection, Map, Dictionary) : 集合中没有元素<br>
	 * 
	 * @param value
	 * @return
	 * @author yangz
	 * @date May 6, 2010 4:21:56 PM
	 */
	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		if ((value instanceof String) && ((StringUtil.trim((String) value).length() <= 0) || "null".equalsIgnoreCase((String) value))) {
			return true;
		}
		if ((value instanceof Object[]) && (((Object[]) value).length <= 0)) {
			return true;
		}
		if (value instanceof Object[]) { // all item in [] are null :
			// 数组中所有元素为空
			Object[] t = (Object[]) value;
			for (int i = 0; i < t.length; i++) {
				if (t[i] != null) {
					if (t[i] instanceof String) {
						if ((StringUtil.trim((String) t[i])).length() > 0 || "null".equalsIgnoreCase((String) t[i])) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
			return true;
		}
		if ((value instanceof Collection) && ((Collection<?>) value).size() <= 0) {
			return true;
		}
		if ((value instanceof Dictionary) && ((Dictionary<?, ?>) value).size() <= 0) {
			return true;
		}
		if ((value instanceof Map) && ((Map<?, ?>) value).size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 对象是否是值类型.
	 * 
	 * @param obj
	 * @return
	 * @author yangz
	 * @date 2012-9-26 下午03:01:44
	 */
	public static boolean isValue(Object obj) {
		if (obj == null || obj instanceof String || obj instanceof Number || obj instanceof Boolean || obj instanceof Character || obj instanceof Date) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否是集合.
	 * 
	 * @param obj
	 * @return
	 * @author yangz
	 * @date 2012-9-26 下午03:50:55
	 */
	public static boolean isCollection(Object obj) {
		if (obj instanceof Collection<?>) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否是数组.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isArray(Object obj) {
		if (obj.getClass().isArray()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否是MAP.
	 * 
	 * @param obj
	 * @return
	 * @author yangz
	 * @date 2013-2-8 下午4:35:30
	 */
	public static boolean isMap(Object obj) {
		if (obj instanceof Map<?, ?>) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将map值转化成对象.<br>
	 * 支持对象类型参见:<MapBeanConventTestObject>
	 * 
	 * @param values
	 * @param type
	 * @return
	 * @author yangz
	 * @date 2012-9-26 下午03:39:54
	 */
	public static <T> T toBean(Map<String, Object> values, Class<T> type) {
		T obj = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
			obj = type.newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor descriptor : propertyDescriptors) {
				String propertyName = descriptor.getName();
				if (!propertyName.equalsIgnoreCase(Class.class.getSimpleName())) {
					Object value = values.get(propertyName);
					Type gType = ClassUtil.getField(type, propertyName).getGenericType();
					copyProperty(obj, propertyName, keyValueToObject(value, descriptor.getPropertyType(), gType));
				}
			}
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
		return obj;
	}

	/**
	 * 键值对转对象.
	 * 
	 * @param value
	 * @param type
	 * @return
	 * @author yangz
	 * @date 2013-4-13 下午10:52:11
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object keyValueToObject(Object value, Class type, Type gType) {
		try {
			if (ClassUtil.isValueType(type)) {
				return ValueUtil.castValue(value, type);
			} else if (Collection.class.isAssignableFrom(type)) {
				Collection resultCollection = (Collection) value.getClass().newInstance();
				Collection collection = (Collection) value;
				for (Object item : collection) {
					resultCollection.add(keyValueToObject(item, (Class) ((ParameterizedType) gType).getActualTypeArguments()[0], null));
				}
				return resultCollection;
			} else {
				// 子对象
				Map<String, Object> values = (Map<String, Object>) value;
				BeanInfo beanInfo = Introspector.getBeanInfo(type);
				Object obj = type.newInstance();
				PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					String propertyName = propertyDescriptor.getName();
					if (!propertyName.equalsIgnoreCase(Class.class.getSimpleName())) {
						Object v = values.get(propertyName);
						Type subGType = ClassUtil.getField(type, propertyName).getGenericType();
						copyProperty(obj, propertyName, keyValueToObject(v, propertyDescriptor.getPropertyType(), subGType));
					}
				}
				return obj;
			}
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map. 支持对象类型参见:<MapBeanConventTestObject>
	 * 
	 * @param bean
	 * @return
	 * @author yangz
	 * @date 2012-9-26 下午03:40:56
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Object> toMap(Object bean) {
		Map<String, Object> returnMap;
		try {
			Class<?> type = bean.getClass();
			returnMap = new HashMap<String, Object>();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor descriptor : propertyDescriptors) {
				String propertyName = descriptor.getName();
				if (!propertyName.equalsIgnoreCase(Class.class.getSimpleName())) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean);
					if (ObjectUtil.isValue(result)) {
						returnMap.put(propertyName, result);
					} else if (ObjectUtil.isCollection(result)) {
						Collection<?> collectionResult = (Collection<?>) result;
						Collection collection = (Collection) result.getClass().newInstance();
						for (Object o : collectionResult) {
							if (ObjectUtil.isValue(o)) {
								collection.add(o);
							} else {
								collection.add(toMap(o));
							}
						}
						returnMap.put(propertyName, collection);
					} else if (result.getClass().isArray()) {
						throw new UnCaughtException("bean property can't be array");
					} else { // 自定义对象
						returnMap.put(propertyName, toMap(result));
					}
				}
			}
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
		return returnMap;
	}

	/**
	 * 更新bean属性, 仅当source有值时更新.<br>
	 * 
	 * 
	 * @param target
	 *            目标被拷贝的属性对象.
	 * @param source
	 *            要覆盖的值.
	 * @author yangz
	 */
	public static void updateBean(Object target, Object source) {
		try {
			if (source != null) {
				PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(source.getClass()).getPropertyDescriptors(); // 获取类属性
				for (PropertyDescriptor descriptor : propertyDescriptors) {
					String propertyName = descriptor.getName();
					if (!propertyName.equalsIgnoreCase(Class.class.getSimpleName())) {
						Field field = ClassUtil.getField(target.getClass(), propertyName);
						Object value = descriptor.getReadMethod().invoke(source);
						try {
							if (ObjectUtil.isEmpty(value)) {
								if (!ClassUtil.hasAnnotation(field, UpdateOnlyNotEmpty.class)) {
									copyProperty(target, propertyName, value);
								}
							} else {
								copyProperty(target, propertyName, value);
							}
						} catch (Exception e) {
						}
					}
				}
			}
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 对象属性赋值.
	 * 
	 * @param target
	 * @param property
	 * @param value
	 * @author yangz
	 * @date 2013-4-13 下午11:15:22
	 */
	public static void copyProperty(Object target, String property, Object value) {
		try {
			BeanUtils.copyProperty(target, property, value);
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 对象属性拷贝.
	 * 
	 * @param desc
	 *            目标对象
	 * @param source
	 *            源对象
	 * @author yangz
	 * @date 2013-3-29 下午4:53:01
	 */
	public static void copyProperties(Object desc, Object source) {
		try {
			BeanUtils.copyProperties(desc, source);
		} catch (Exception ex) {
			throw new UnCaughtException(ex);
		}
	}

	/**
	 * 集合拷贝.
	 * 
	 * @param srcList
	 * @param destClass
	 * @return
	 * @author Dipin
	 * @date 2013-4-12 下午3:01:29
	 */
	public static final <K, E> List<E> listCopy(List<K> srcList, Class<E> destClass) {
		if (srcList == null) {
			return CollectionUtil.emptyList();
		}
		// 转换结果值
		List<E> result = new ArrayList<E>(srcList.size());
		for (K srcObj : srcList) {
			E destObj = null;
			try {
				destObj = destClass.newInstance();
				copyProperties(destObj, srcObj);
			} catch (Exception ex) {
				throw new UnCaughtException(ex);
			}
			result.add(destObj);
		}
		return result;
	}
}
