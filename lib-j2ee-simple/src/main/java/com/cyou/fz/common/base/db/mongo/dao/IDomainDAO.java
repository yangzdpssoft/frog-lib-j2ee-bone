package com.cyou.fz.common.base.db.mongo.dao;


import com.cyou.fz.common.base.vo.PaginationVO;

import java.util.List;
import java.util.Map;


/**
 * <p>Description: 领域对象DAO.</p>
 * <p>Company: cyou</p>
 * @author yangz
 * @date 2013-7-4
 * @version V1.0
 */
public interface IDomainDAO<T> {
    /**
     * 保存和更新.
     * @param t
     * @return
     */
    String saveOrUpdate(T t);

    /**
     * 获取领域对象.
     * @param id
     * @return
     */
    T get(String id, int fetchDeep);

    /**
     * 获取领域对象, 默认抓取1层.
     * @param id
     * @return
     */
    T get(String id);
    /**
     * 获取领域对象, 取部分属性.
     * @param id
     * @param fields
     * @return
     */
    T get(String id, String... fields);
    /**
     * 根据业务唯一列获取领域对象.
     * @param key
     * @param value
     * @return
     */
    T unique(String key, Object value, int fetchDeep);
    /**
     * 根据业务唯一列获取领域对象.
     * @param key
     * @param value
     * @param fields
     * @return
     */
    T unique(String key, Object value, String... fields);

    /**
     * 根据业务唯一列获取领域对象,默认抓取1层.
     * @param key
     * @param value
     * @return
     */
    T unique(String key, Object value);
    /**
     * 删除.
     * @param id
     */
    void delete(String... id);

    /**
     * 分页查询,默认级联抓取1层.
     * @param cond
     * @param sort
     * @param pageNo
     * @param pageSize
     * @return
     */
    PaginationVO<T> query(Map<String, Object> cond, Map<String, Object> sort, int pageNo, int pageSize);
    /**
     * 分页查询.
     * @param cond
     * @param pageNo
     * @param pageSize
     * @return
     */
    PaginationVO<T> query(Map<String, Object> cond, Map<String, Object> sort, int pageNo, int pageSize, int fetchDeep);
    /**
     * 查询分页数据, 取部分属性.
     * @param cond
     * @param sort
     * @param pageNo
     * @param pageSize
     * @param fields
     * @return
     */
    PaginationVO<T> query(Map<String, Object> cond, Map<String, Object> sort, int pageNo, int pageSize, String... fields);

    /**
     * 查询所有数据,默认级联抓取1层.
     * @param cond
     * @param sort
     * @return
     */
    List<T> queryAll(Map<String, Object> cond, Map<String, Object> sort);
    /**
     * 查询所有对象.
     * @param cond
     * @return
     */
    List<T> queryAll(Map<String, Object> cond, Map<String, Object> sort, int fetchDeep);

    /**
     * 查询所有数据, 取部分属性.
     * @param cond
     * @param sort
     * @param fields
     * @return
     */
    List<T> queryAll(Map<String, Object> cond, Map<String, Object> sort, String... fields);
    /**
     * 更新部分属性.
     * @param fields
     * @param id
     */
    void update(Map<String, Object> fields, String... id);

}
