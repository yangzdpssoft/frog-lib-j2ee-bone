package com.cyou.fz.common.base.db.mongo.annotation;

import com.cyou.fz.common.base.db.mongo.connection.MongoType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
/**
 * <p>Description: 可重复读.</p>
 * <p>Company: cyou</p>
 * @author yangz
 * @date 2013-7-3
 * @version V1.0
 */
public @interface ReReadEnable {
	public MongoType value() default MongoType.WRITE;
}
