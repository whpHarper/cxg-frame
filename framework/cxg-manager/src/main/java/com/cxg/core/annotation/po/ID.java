package com.cxg.core.annotation.po;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @ClassName: ID
* @Description: 定义PO类的键类型
* @author haipeng
* @date 2017年1月2日
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ID {

	/**
	 * 主键数据类型，默认整数型
	 * @return
	 */
	int type() default PUBVALUE.TABLE_ID_TYPE_INTEGER;
	
}
