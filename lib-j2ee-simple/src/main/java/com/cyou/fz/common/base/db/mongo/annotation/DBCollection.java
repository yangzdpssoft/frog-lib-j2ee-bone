package com.cyou.fz.common.base.db.mongo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Description: JAVA Bean映射mongodb集合注解
 * @Company : cyou
 * @author yangz
 * @date   2012-9-27 上午09:08:29
 * @version V1.0
 */
@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
@Inherited  
public @interface DBCollection {
	public String value();
}
