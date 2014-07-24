package com.cyou.fz.common.crud.property;

/**
 * 时间戳标志位
 * 
 * @author yangz
 * @date Mar 22, 2011 9:14:07 AM
 */
public interface IUpdateTime {
	
	public final static String PROPERTY = "updateTime";
	
	public final static String COLUMN = "update_time";
	
	public Long getUpdateTime();

	public void setUpdateTime(Long ts);

}
