package com.cyou.fz.common.crud.property;

import javax.persistence.MappedSuperclass;

/**
 * 主键
 * 
 * @author yangz
 * @date   Mar 22, 2011 9:17:58 AM
 */
@MappedSuperclass
public interface IId {
	public final static String PROPERTY = "id";
	
	public final static String COLUMN = "id";

	public String getId();

	public void setId(String id);

}
