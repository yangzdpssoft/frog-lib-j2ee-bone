package com.cyou.fz.common.base.db.mongo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 注解JAVA BEAN 部分属性不映射到mongodb
 * @Company : cyou
 * @author yangz
 * @date   2012-9-27 上午09:18:02
 */
@Target(ElementType.FIELD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
@Inherited  
public @interface Ignore {

}
