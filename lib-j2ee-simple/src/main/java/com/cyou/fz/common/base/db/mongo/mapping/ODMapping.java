package com.cyou.fz.common.base.db.mongo.mapping;


import com.cyou.fz.common.base.db.mongo.annotation.DBCollection;
import com.cyou.fz.common.base.db.mongo.annotation.DBRef;
import com.cyou.fz.common.base.db.mongo.annotation.Ignore;
import com.cyou.fz.common.base.db.mongo.constants.DBFieldConstant;
import com.cyou.fz.common.base.exception.UnCaughtException;
import com.cyou.fz.common.base.util.ClassUtil;
import com.cyou.fz.common.base.util.ObjectUtil;
import com.cyou.fz.common.base.util.ValueUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>mongo 持久化对象映射</p>
 * <p>Description: </p>
 * <p>Company: cyou</p>
 *
 * @author yangz
 * @version V1.0
 * @date 2013-7-1
 */
public class ODMapping {
    /**
     * bean主键.
     */
    public final static String ID = "id";
    /**
     * java bean to DBObject.
     *
     * @param db
     * @param bean
     * @return
     */
    public static DBObject toDBObject(DB db, Object bean) {
        DBObject result = new BasicDBObject();
        try {
            Class type = bean.getClass();
            BeanInfo beanInfo = ClassUtil.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Object value = descriptor.getReadMethod().invoke(bean, new Object[0]);
                    Field field = ClassUtil.getField(type, propertyName);
                    if (!ObjectUtil.isEmpty(value)) {
                        if (ClassUtil.hasAnnotation(field, DBRef.class)) {
                            if (ObjectUtil.isCollection(value)) {
                                Collection<?> collectionValue = (Collection<?>) value;
                                Collection dbCollection = (Collection) ClassUtil.newInstance(value.getClass());
                                for (Object o : collectionValue) {
                                    dbCollection.add(buildDBRef(db, o));
                                }
                                result.put(propertyName, dbCollection);
                            } else {
                                result.put(propertyName, buildDBRef(db, value));
                            }
                        } else if (!ClassUtil.hasAnnotation(field, Ignore.class)) {
                            if (ObjectUtil.isValue(value)) {
                                if (ID.equals(propertyName)) {
                                    result.put(DBFieldConstant._ID, new ObjectId(ValueUtil.getString(value)));
                                } else {
                                    result.put(propertyName, value);
                                }
                            } else if (ObjectUtil.isCollection(value)) {
                                Collection<?> collectionValue = (Collection<?>) value;
                                Collection dbCollection = (Collection) ClassUtil.newInstance(value.getClass());
                                for (Object o : collectionValue) {
                                    dbCollection.add(toDBObject(db, o));
                                }
                                result.put(propertyName, dbCollection);
                            } else if (ObjectUtil.isArray(value)) {
                                throw new UnCaughtException("property type can't be array, propertyName : " + propertyName);
                            } else {
                                result.put(propertyName, toDBObject(db, value));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new UnCaughtException(e);
        }
        return result;
    }

    /**
     * 构造DBRef.
     * @param db
     * @param value
     * @return
     */
    private static com.mongodb.DBRef buildDBRef(DB db, Object value){
        String id = (String) ClassUtil.getPropertyValue(value, ID);
        String collection = value.getClass().getAnnotation(DBCollection.class).value();
        return new com.mongodb.DBRef(db, collection, new ObjectId(id));
    }
    /**
     * DBObject to java bean.
     *
     * @param dBObject
     * @param clz
     * @param fetchDeep 抓取的层数.
     * @param <T>
     * @return
     */
    public static <T> T toObject(DBObject dBObject, Class<T> clz, int fetchDeep) {
        try {
            if (ObjectUtil.isEmpty(dBObject)) {
                return null;
            } else {
                T t = (T) ClassUtil.newInstance(clz);
                Map map = dBObject.toMap();
                BeanInfo beanInfo = Introspector.getBeanInfo(clz); // 获取类属性
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    PropertyDescriptor descriptor = propertyDescriptors[i];
                    String propertyName = descriptor.getName();
                    String dbKey = ID.equals(propertyName) ? DBFieldConstant._ID : propertyName;
                    if (map.containsKey(dbKey)) {
                        Object value = map.get(dbKey);
                        Object[] args = new Object[1];
                        if (ObjectUtil.isValue(value) || value == null) {
                            args[0] = value;
                        } else if (value instanceof ObjectId) {
                            args[0] = value.toString();
                        } else if (value instanceof com.mongodb.DBRef) {
                            if (fetchDeep > 0) {
                                com.mongodb.DBRef ref = (com.mongodb.DBRef) value;
                                DBObject refObj = ref.fetch();
                                args[0] = toObject(refObj, (Class) descriptor.getReadMethod().getReturnType(), fetchDeep - 1);
                            }
                        } else if (value instanceof BasicDBList) {
                            BasicDBList valueList = (BasicDBList) value;
                            Collection collection = null;
                            Class returnType = (Class) descriptor.getReadMethod().getReturnType();
                            Class actualType = ClassUtil.getActualType(ClassUtil.getField(clz, propertyName).getGenericType());
                            if (List.class.isAssignableFrom(returnType)) {
                                collection = new ArrayList();
                            } else if (Set.class.isAssignableFrom(returnType)) {
                                collection = new HashSet();
                            }
                            for (Object o : valueList) {
                                if (o instanceof DBObject) {
                                    DBObject dbO = (DBObject) o;
                                    collection.add(toObject(dbO, actualType, fetchDeep - 1));
                                } else if (o instanceof com.mongodb.DBRef) {
                                    if (fetchDeep > 0) {
                                        com.mongodb.DBRef ref = (com.mongodb.DBRef) o;
                                        DBObject refObj = ref.fetch();
                                        collection.add(toObject(refObj, actualType, fetchDeep - 1));
                                    }
                                } else if (ObjectUtil.isValue(o)) {
                                    collection.add(o);
                                }
                            }
                            args[0] = collection;
                        } else if (value instanceof BasicDBObject) {
                            args[0] = toObject((DBObject) value, (Class) descriptor.getReadMethod().getReturnType(), fetchDeep - 1);
                        }
                        descriptor.getWriteMethod().invoke(t, args);
                    }
                }
                return t;
            }
        } catch (Exception e) {
            throw new UnCaughtException(e);
        }
    }
}
