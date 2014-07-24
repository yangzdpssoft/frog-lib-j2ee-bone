package com.cyou.fz.common.crud.property;

/**
 * 时间戳标志位
 * 
 * @author yangz
 * @date Mar 22, 2011 9:14:07 AM
 */
public interface IDeleteTime {
	
	public final static String PROPERTY = "deleteTime";
	
	public final static String COLUMN = "delete_time";
	
	public Long getDeleteTime();

	public void setDeleteTime(Long ts);

}
