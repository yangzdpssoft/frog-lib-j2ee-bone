package com.cyou.fz.common.base.vo;


/**
 * 下拉选择框
 * 
 * @Company : cyou
 * @author yangz
 * @date 2012-10-12 下午04:33:22
 */
public class SelectOptionVO {
	/**
	 * 下拉的值
	 */
	private String id;
	/**
	 * 下拉显示名称
	 */
	private String name;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SelectOptionVO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SelectOptionVO() {
		super();
	}

}
