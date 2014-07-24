package com.cyou.fz.common.base.util.annotation;

import java.lang.annotation.*;

/**
 * <p>Description: 当不为空时更新注解标记.目前用于com.cyou.fz.frog.common.utils.ObjectUtil#updateBean(java.lang.Object, java.lang.Object)</p>
 * <p>Company: cyou</p>
 * @author yangz
 * @date 2013-7-24
 * @version V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UpdateOnlyNotEmpty {
}
