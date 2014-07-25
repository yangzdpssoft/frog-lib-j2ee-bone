package com.cyou.fz.common.base.db.mongo.dao;

import com.cyou.fz.common.base.constant.FieldConstant;
import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.base.db.mongo.constants.DBFieldConstant;
import com.cyou.fz.common.base.db.mongo.mapping.ODMapping;
import com.cyou.fz.common.base.db.mongo.util.MongoUtil;
import com.cyou.fz.common.base.exception.UnCaughtException;
import com.cyou.fz.common.base.util.ClassUtil;
import com.cyou.fz.common.base.util.MathUtil;
import com.cyou.fz.common.base.util.ObjectUtil;
import com.cyou.fz.common.base.vo.PaginationVO;
import com.cyou.fz.common.crud.property.IId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 领域对象mongo存储层.
 * @author yangz
 * @date 2014/7/24
 *
 */

public class MongoDomainDAO<T> {

    @Autowired
    private MongoDAO mongoDAO;


    /**
     * 领域对象数据库表名.
     */
    private String tableName;
    /**
     * 领域对象类型.
     */
    private Class<T> domainClass;

    public MongoDomainDAO() {
        Class<T> domianClass = ClassUtil.getActualType(this.getClass());
        DBCollection annotation = domianClass.getAnnotation(DBCollection.class);
        if (annotation == null) {
            throw new UnCaughtException("实体需注解表名.");
        } else {
            this.domainClass = domianClass;
            this.tableName = annotation.value();
        }
    }

    public String saveOrUpdate(T t) {
        DBObject dbObject = null;
        if(t instanceof IId){
            IId m = (IId) t;
            if (!ObjectUtil.isEmpty(m.getId())){
                T temp = get(m.getId());
                ObjectUtil.updateBean(temp, t);
                dbObject = ODMapping.toDBObject(mongoDAO.getDB(), temp);
            }else {
                dbObject = ODMapping.toDBObject(mongoDAO.getDB(), t);
            }
        }
        mongoDAO.saveOrUpdate(tableName, dbObject);
        String id = dbObject.get(DBFieldConstant._ID).toString();
        ObjectUtil.copyProperty(t, FieldConstant.ID, id);
        return id;
    }

    
    public void update(Map<String, Object> fields, String... id){
        DBObject dbObject = new BasicDBObject();
        dbObject.putAll(fields);
        mongoDAO.update(tableName, dbObject, id);
    }


    public void inc(String id, String field, long value){
        mongoDAO.inc(tableName, id, field, value);
    }

    public T get(String id) {
        return get(id);
    }

    public T get(String id, int fetchDeep) {
        DBObject dbObject = mongoDAO.get(tableName, id);
        return ODMapping.toObject(dbObject, domainClass, fetchDeep);
    }

    public T get(String id, String... fields){
        return ODMapping.toObject(mongoDAO.get(tableName, id, MongoUtil.conventFields(fields)), domainClass, 0);
    }

    public T unique(String key, Object value) {
        return unique(key, value, 1);
    }

    public T unique(String key, Object value, int fetchDeep) {
        DBObject cond = new BasicDBObject();
        cond.put(key, value);
        return ODMapping.toObject(mongoDAO.findOne(tableName, cond), domainClass, fetchDeep);
    }

    public T unique(String key, Object value, String... fields) {
        DBObject cond = new BasicDBObject();
        cond.put(key, value);
        return ODMapping.toObject(mongoDAO.findOne(tableName, cond, MongoUtil.conventFields(fields)), domainClass, 0);
    }

    public void delete(String... id) {
        mongoDAO.delete(tableName, id);
    }

    
    public PaginationVO<T> query(Map<String, Object> cond, Map<String, Object> sort, int pageNo, int pageSize) {
        return query(cond, sort, pageNo, pageSize, 1);
    }

    
    public PaginationVO<T> query(Map<String, Object> cond, Map<String, Object> sort, int pageNo, int pageSize, String... fields) {
        DBObject dbObjectCond = MongoUtil.conventCondQueryBuilder(cond, domainClass).get();
        DBObject sortDbo = new BasicDBObject();
        sortDbo.putAll(sort);
        List<DBObject> list = mongoDAO.query(tableName, dbObjectCond, sortDbo, MongoUtil.conventFields(fields), MathUtil.getSkipRow(pageNo, pageSize), pageSize);
        int rowCount = mongoDAO.count(tableName, dbObjectCond);
        PaginationVO<T> result = new PaginationVO<T>(rowCount, pageNo, pageSize);
        List<T> listData = new ArrayList<T>();
        for (DBObject o : list) {
            listData.add(ODMapping.toObject(o, domainClass, 0));
        }
        result.setListData(listData);
        return result;
    }

    
    public PaginationVO<T> query(Map<String, Object> cond, Map<String, Object> sort, int pageNo, int pageSize, int fetchDeep) {
        DBObject dbObjectCond = MongoUtil.conventCondQueryBuilder(cond, domainClass).get();
        DBObject sortDbo = new BasicDBObject();
        sortDbo.putAll(sort);
        List<DBObject> list = mongoDAO.query(tableName, dbObjectCond, sortDbo, MathUtil.getSkipRow(pageNo, pageSize), pageSize);
        int rowCount = mongoDAO.count(tableName, dbObjectCond);
        PaginationVO<T> result = new PaginationVO(rowCount, pageNo, pageSize);
        List<T> listData = new ArrayList();
        for (DBObject o : list) {
            listData.add(ODMapping.toObject(o, domainClass, fetchDeep));
        }
        result.setListData(listData);
        return result;
    }

    
    public List<T> queryAll(Map<String, Object> cond, Map<String, Object> sort) {
        return queryAll(cond, sort, 1);
    }

    
    public List<T> queryAll(Map<String, Object> cond, Map<String, Object> sort, String... fields) {
        DBObject dbObjectCond = MongoUtil.conventCondQueryBuilder(cond, domainClass).get();
        DBObject sortDBo = new BasicDBObject();
        sortDBo.putAll(sort);
        List<DBObject> list = mongoDAO.queryAll(tableName, dbObjectCond, MongoUtil.conventFields(fields), sortDBo);
        List<T> result = new ArrayList();
        for (DBObject o : list) {
            result.add(ODMapping.toObject(o, domainClass, 0));
        }
        return result;
    }

    
    public List<T> queryAll(Map<String, Object> cond, Map<String, Object> sort, int fetchDeep) {
        DBObject dbObjectCond = MongoUtil.conventCondQueryBuilder(cond, domainClass).get();
        DBObject sortDBo = new BasicDBObject();
        sortDBo.putAll(sort);
        List<DBObject> list = mongoDAO.queryAll(tableName, dbObjectCond, sortDBo);
        List<T> result = new ArrayList();
        for (DBObject o : list) {
            result.add(ODMapping.toObject(o, domainClass, fetchDeep));
        }
        return result;
    }

    public MongoDAO getMongoDAO() {
        return mongoDAO;
    }

    public void setMongoDAO(MongoDAO mongoDAO) {
        this.mongoDAO = mongoDAO;
    }
}
