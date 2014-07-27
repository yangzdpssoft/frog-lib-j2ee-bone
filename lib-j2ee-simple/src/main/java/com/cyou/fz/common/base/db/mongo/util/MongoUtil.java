package com.cyou.fz.common.base.db.mongo.util;

import com.cyou.fz.common.base.constant.CondPrefixConstant;
import com.cyou.fz.common.base.constant.FieldConstant;
import com.cyou.fz.common.base.db.mongo.constants.DBFieldConstant;
import com.cyou.fz.common.base.db.mongo.constants.DBTableConstant;
import com.cyou.fz.common.base.db.mongo.dao.MongoDAO;
import com.cyou.fz.common.base.util.ClassUtil;
import com.cyou.fz.common.base.util.ObjectUtil;
import com.cyou.fz.common.base.util.StringUtil;
import com.cyou.fz.common.base.util.ValueUtil;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.*;
import java.util.regex.Pattern;

/**
 * mongo工具类.
 * @author yangz
 * @date 2014/7/24
 *
 */

public class MongoUtil {

    /**
     * 批量转换ObjectId.
     *
     * @param id
     * @return
     */
    public static ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }

    public static DBObject conventFields(String... fields){
        DBObject dbFields = new BasicDBObject();
        for (int i = 0; i < fields.length; i++) {
            dbFields.put(fields[i], fields[i]);
        }
        return dbFields;
    }

    /**
     * 将查询条件转mongo查询条件.<br>
     * 1、不支持OR.<br>
     * 2、支持 =, !=, in, not in, <=, >= 等条件查询.<br>
     * 3、复杂的条件请使用原生的查询条件构造.<br>
     *
     * @param cond
     * @param domainClass
     *            字符串条件使用正则匹配.
     * @return
     */
    public static QueryBuilder conventCondQueryBuilder(Map<String, Object> cond, Class<?> domainClass) {
        QueryBuilder qb = QueryBuilder.start();
        for (Map.Entry<String, Object> item : cond.entrySet()) {
            String condKey = castCondKeyPrefix(item.getKey());
            Object condValue = item.getValue();
            if (!ObjectUtil.isEmpty(condValue)) {
                if (condKey.contains("_")) {
                    Map<String, Object> subCond = new HashMap();
                    String chainKey = condKey.substring(0, condKey.lastIndexOf("_"));
                    String[] chain = chainKey.split("_");
                    Class subClass = domainClass;
                    for (String c : chain) {
                        subClass = ClassUtil.getPropertyType(subClass, c);
                    }
                    String subCondKey = condKey.substring(condKey.lastIndexOf("_") + 1, condKey.length());
                    subCond.put(subCondKey, condValue);
                    Map<String, Object> subCondValue = conventCondQueryBuilder(subCond, subClass).get().toMap();
                    for (Map.Entry<String, Object> valueItem : subCondValue.entrySet()) {
                        DBObject dbo = new BasicDBObject();
                        StringBuilder sb = new StringBuilder(50);
                        if (FieldConstant.ID.equals(subCondKey)) {
                            sb.append(chainKey).append(".$").append(FieldConstant.ID);
                        } else {
                            sb.append(chainKey).append(".").append(valueItem.getKey());
                        }
                        dbo.put(sb.toString(), valueItem.getValue());
                        qb.and(dbo);
                    }
                }else{
                    if(condKey.startsWith(CondPrefixConstant.GT_)){
                        String propertyKey = castPropertyKey(condKey, CondPrefixConstant.GT_);
                        String mongoKey = castMongoKey(propertyKey);
                        if(ClassUtil.hasProperty(domainClass, propertyKey)){
                            Object value = castValue(mongoKey, condValue, ClassUtil.getPropertyType(domainClass, propertyKey));
                            qb.put(mongoKey).greaterThanEquals(value);
                        }
                    }else if(condKey.startsWith(CondPrefixConstant.LT_)){
                        String propertyKey = castPropertyKey(condKey, CondPrefixConstant.LT_);
                        String mongoKey = castMongoKey(propertyKey);
                        if(ClassUtil.hasProperty(domainClass, propertyKey)){
                            Object value = castValue(mongoKey, condValue, ClassUtil.getPropertyType(domainClass, propertyKey));
                            qb.put(mongoKey).lessThanEquals(value);
                        }
                    }else if(condKey.startsWith(CondPrefixConstant.NEQ_)){
                        String propertyKey = castPropertyKey(condKey, CondPrefixConstant.NEQ_);
                        String mongoKey = castMongoKey(propertyKey);
                        if(ClassUtil.hasProperty(domainClass, propertyKey)){
                            Object value = castValue(mongoKey, condValue, ClassUtil.getPropertyType(domainClass, propertyKey));
                            qb.put(mongoKey).notEquals(value);
                        }
                    }else if(condKey.startsWith(CondPrefixConstant.IN_)){
                        String propertyKey = castPropertyKey(condKey, CondPrefixConstant.IN_);
                        String mongoKey = castMongoKey(propertyKey);
                        if(ClassUtil.hasProperty(domainClass, propertyKey)){
                            Object value = castValue(mongoKey, condValue, ClassUtil.getPropertyType(domainClass, propertyKey));
                            qb.put(mongoKey).in(value);
                        }
                    }else if(condKey.startsWith(CondPrefixConstant.NIN_)){
                        String propertyKey = castPropertyKey(condKey, CondPrefixConstant.NIN_);
                        String mongoKey = castMongoKey(propertyKey);
                        if(ClassUtil.hasProperty(domainClass, propertyKey)){
                            Object value = castValue(mongoKey, condValue, ClassUtil.getPropertyType(domainClass, propertyKey));
                            qb.put(mongoKey).notIn(value);
                        }
                    }else if(condKey.startsWith(CondPrefixConstant.REGEX_)){
                        String propertyKey = castPropertyKey(condKey, CondPrefixConstant.REGEX_);
                        String mongoKey = castMongoKey(propertyKey);
                        if(ClassUtil.hasProperty(domainClass, propertyKey)){
                            Object value = castValue(mongoKey, condValue, ClassUtil.getPropertyType(domainClass, propertyKey));
                            Pattern pattern = StringUtil.getInsensitivePattern(ValueUtil.getString(value));
                            qb.put(mongoKey).regex(pattern);
                        }
                    }else {
                        String propertyKey = condKey;
                        String mongoKey = castMongoKey(propertyKey);
                        if(ClassUtil.hasProperty(domainClass, propertyKey)){
                            Object value = castValue(mongoKey, condValue, ClassUtil.getPropertyType(domainClass, propertyKey));
                            qb.put(mongoKey).is(value);
                        }
                    }
                }
            }
        }
        return qb;
    }

    /**
     * 转换 mongo 查询值.
     *
     * @param mongoKey
     * @param condValue
     * @param propertyType
     * @return
     */
    private static Object castValue(String mongoKey, Object condValue, Class propertyType) {
        if (DBFieldConstant._ID.equals(mongoKey)) {
            if(ObjectUtil.isCollection(condValue) || ObjectUtil.isArray(condValue)){
                Collection valueCollection = (Collection) condValue;
                List<ObjectId> dbCollection = new ArrayList();
                for (Object o : valueCollection) {
                    dbCollection.add(new ObjectId(o.toString()));
                }
                return dbCollection;
            }else{
                return new ObjectId(condValue.toString());
            }
        } else if (ObjectUtil.isCollection(condValue)) {
            Collection valueCollection = (Collection) condValue;
            List dbCollection = new ArrayList();
            for (Object o : valueCollection) {
                dbCollection.add(ValueUtil.castValue(o, propertyType));
            }
            return dbCollection;
        }else if(ObjectUtil.isArray(condValue)){
            Object[] array = (Object[]) condValue;
            List dbCollection = new ArrayList();
            for (Object o : array) {
                dbCollection.add(ValueUtil.castValue(o, propertyType));
            }
            return dbCollection;
        } else {
            return ValueUtil.castValue(condValue, propertyType);
        }
    }
    /**
     * 将 condKey 转换为 mongoKey;
     *
     * @param propertyKey
     *            可以是属性键.
     * @return
     */
    private static String castMongoKey(String propertyKey) {
        if (FieldConstant.ID.equals(propertyKey)) {
            return DBFieldConstant._ID;
        } else {
            return propertyKey;
        }
    }

    /**
     * 将mongoKey转换为属性key.
     *
     * @param condKey
     * @return
     */
    private static String castPropertyKey(String condKey, String prefix) {
        return StringUtil.clearPrefix(condKey, prefix);
    }

    /**
     * 去掉查询条件前缀.
     * @param condKey
     * @return
     */
    private static String castCondKeyPrefix(String condKey){
        return StringUtil.clearPrefix(condKey, "cond!");
    }


    /**
     * 标记DBREF键值.
     * @param dbObj
     */
    public static void markFK(MongoDAO dao, DBObject dbObj){
        if(dbObj != null){
            Map<String, Object> map = dbObj.toMap();
            for(Map.Entry<String, Object> m : map.entrySet()){
                Object value = m.getValue();
                if(value != null){
                    if(value instanceof com.mongodb.DBRef){
                        saveFK(dao, (com.mongodb.DBRef) value);
                    }else if (value instanceof List){
                        List valueList = (List) value;
                        for (Object o : valueList) {
                            if (o instanceof DBObject) {
                                DBObject dbO = (DBObject) o;
                                markFK(dao, dbO);
                            }else if(o instanceof com.mongodb.DBRef){
                                saveFK(dao, (com.mongodb.DBRef) o);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 标记DBREF键值.
     * @param dbObj
     */
    public static void removeMarkFK(MongoDAO dao, DBObject dbObj){
        if(dbObj != null){
            Map<String, Object> map = dbObj.toMap();
            for(Map.Entry<String, Object> m : map.entrySet()){
                Object value = m.getValue();
                if(value != null){
                    if(value instanceof com.mongodb.DBRef){
                        saveFK(dao, (com.mongodb.DBRef) value);
                    }else if (value instanceof List){
                        List valueList = (List) value;
                        for (Object o : valueList) {
                            if (o instanceof DBObject) {
                                DBObject dbO = (DBObject) o;
                                removeMarkFK(dao, dbO);
                            }else if(o instanceof com.mongodb.DBRef){
                                deleteFK(dao, (com.mongodb.DBRef) o);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 保存外键.
     * @param dao
     * @param id
     */
    public static void saveFK(MongoDAO dao, com.mongodb.DBRef id){
        DBCollection collection = dao.getDB().getCollection(DBTableConstant.FK);
        if(id != null){
            DBObject dbo = new BasicDBObject();
            dbo.put(DBFieldConstant._ID, id.getId());
            DBObject inc = new BasicDBObject("$inc", new BasicDBObject(DBFieldConstant.FK_NUM, 1));
            collection.findAndModify(dbo, null, null, false, inc, false, true);
        }
    }

    /**
     * 保存外键.
     * @param dao
     * @param id
     */
    public static void deleteFK(MongoDAO dao, com.mongodb.DBRef id){
        DBCollection collection = dao.getDB().getCollection(DBTableConstant.FK);
        if(id != null){
            DBObject dbo = new BasicDBObject();
            dbo.put(DBFieldConstant._ID, id.getId());
            DBObject inc = new BasicDBObject("$inc", new BasicDBObject(DBFieldConstant.FK_NUM, -1));
            collection.findAndModify(dbo, null, null, false, inc, false, true);
        }
    }

}
