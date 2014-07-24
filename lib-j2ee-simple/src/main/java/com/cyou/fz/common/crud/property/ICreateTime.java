package com.cyou.fz.common.crud.property;

/**
 * 时间戳标志位
 * 
 * @author yangz
 * @date Mar 22, 2011 9:14:07 AM
 */
public interface ICreateTime {
	
	public final static String PROPERTY = "createTime";
	
	public final static String COLUMN = "create_time";

	public Long getCreateTime();

	public void setCreateTime(Long ts);

}
