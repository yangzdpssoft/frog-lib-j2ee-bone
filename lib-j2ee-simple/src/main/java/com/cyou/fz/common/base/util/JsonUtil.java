package com.cyou.fz.common.base.util;

import com.cyou.fz.common.base.exception.UnCaughtException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class JsonUtil {

	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

	/**
	 * 将对象转化为Json格式字符串.
	 * 
	 * @param obj
	 * @return
	 * @throws java.io.IOException
	 * @throws com.fasterxml.jackson.databind.JsonMappingException
	 * @throws com.fasterxml.jackson.core.JsonGenerationException
	 */
	public static String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}

	/**
	 * 将Json格式字符串转化为对象.
	 * 
	 * @param <T>
	 * @param json
	 * @param requiredType
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> requiredType) {
		try {
			return mapper.readValue(json, requiredType);
		} catch (Exception ex) {
			throw new UnCaughtException(ex);
		}
	}

    /**
     * 将Json格式字符串转化为对象.
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, TypeReference<T> type) {
        try {
            return (T)mapper.readValue(json, type);
        }catch (Exception ex) {
            throw new UnCaughtException(ex.getMessage());
        }
    }

    /**
     * 将Json格式字符串转化为对象.
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, JavaType type) {
        try {
            return (T)mapper.readValue(json, type);
        }catch (Exception ex) {
            throw new UnCaughtException(ex.getMessage());
        }
    }
    /**
     * 构造Collection<T>类型.
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
         return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
