package com.cyou.fz.common.base.vo;

import java.util.Collections;
import java.util.List;

/**
 * 分页DTO.
 * 
 * @author Dipin
 * @date 2013-4-11 上午10:18:20
 */
public class PaginationVO<E> extends PageVO implements java.io.Serializable {

	private static final long serialVersionUID = 1451875979747005797L;

	/**
	 * 数据列表.
	 */
	private List<E> listData = Collections.emptyList();

	public PaginationVO(int rows, int pageSize) {
		super(rows, pageSize);
	}
	
	public PaginationVO(int rows, int pageNo, int pageSize) {
		super.init(rows, pageSize, pageNo);
	}

	public PaginationVO() {
		super();
	}

	public List<E> getListData() {
		return listData;
	}

	public void setListData(List<E> datas) {
		this.listData = datas;
	}

}
