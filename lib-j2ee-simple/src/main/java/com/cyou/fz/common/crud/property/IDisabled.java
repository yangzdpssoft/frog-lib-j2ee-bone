package com.cyou.fz.common.crud.property;
/**
 * 锁定字段.
 * 
 * @author yangz
 * @date 2013-6-4 上午9:28:23
 */
public interface IDisabled {
	public final static String PROPERTY = "disabled";
	
	public final static String COLUMN = "disabled";
	
	public Boolean getDisabled();
	
	public void setDisabled(Boolean disable);
}
