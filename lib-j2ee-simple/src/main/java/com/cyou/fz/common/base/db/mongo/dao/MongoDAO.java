package com.cyou.fz.common.base.db.mongo.dao;


import com.cyou.fz.common.base.db.mongo.connection.MongoDBFactory;
import com.cyou.fz.common.base.db.mongo.constants.DBFieldConstant;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>mongo数据库操作</p>
 * <p>Description: </p>
 * <p>Company: cyou</p>
 * @author yangz
 * @date 2013-7-1
 * @version V1.0
 */
public class MongoDAO {

    private final static Object LOCK = new Object();
    /**
     * 数据库名.
     */
    private String dbName = null;

    private MongoDBFactory mongoFactory;

    private String user;
    private String password;

    public Mongo getMongo(){
        return mongoFactory.getMongo();
    }

    public DB getDB(){
        DB db = this.getMongo().getDB(dbName);
        if (!db.isAuthenticated()) {
            synchronized (LOCK) {
                if (!db.isAuthenticated()) {
                    db.authenticate(user, password.toCharArray());
                }
            }
        }
        return db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 保存和更新.
     * @param tableName
     * @param dbObj
     * @return
     */
    public WriteResult saveOrUpdate(String tableName, DBObject dbObj){
        DBCollection table = this.getDB().getCollection(tableName);
        return table.save(dbObj);
    }

    /**
     * 获取单条记录.
     * @param tableName
     * @param id
     * @return
     */
    public DBObject get(String tableName, String id){
        DBCollection table = this.getDB().getCollection(tableName);
        return table.findOne(new BasicDBObject(DBFieldConstant._ID, new ObjectId(id)));
    }

    /**
     * 获取数据部分属性.
     * @param tableName
     * @param id
     * @param fields
     * @return
     */
    public DBObject get(String tableName, String id, DBObject fields){
        DBCollection table = getDB().getCollection(tableName);
        return table.findOne(new BasicDBObject(DBFieldConstant._ID, new ObjectId(id)), fields);
    }

    /**
     * 删除记录.
     * @param id
     */
    public WriteResult delete(String tableName, String... id){
        ObjectId[] oids = toObjectIds(id);
        QueryBuilder queryBuilder = QueryBuilder.start(DBFieldConstant._ID);
        DBObject dbObj = queryBuilder.in(oids).get();
        DBCollection table = this.getDB().getCollection(tableName);
        WriteResult result = table.remove(dbObj);
        return result;
    }

    /**
     * 更新部分属性值.
     * @param tableName
     * @param value
     * @param id
     * @return
     */

    public WriteResult update(String tableName, DBObject value, String... id){
        QueryBuilder queryBuilder = QueryBuilder.start(DBFieldConstant._ID);
        DBObject dbObj = queryBuilder.in(toObjectIds(id)).get();
        DBObject set = new BasicDBObject("$set", value);
        WriteResult result = getDB().getCollection(tableName).updateMulti(dbObj, set);
        return result;
    }
    
    /**
     * 批量更新.
     * @param query
     * @param update
     */
    public WriteResult updateMuti(String tableName, DBObject query, DBObject update){
        return getDB().getCollection(tableName).updateMulti(query, update);
    }

    /**
     * 查询并更新.
     * @param tableName
     * @param query
     * @param update
     * @return
     */
    public DBObject findAndModify(String tableName, DBObject query, DBObject update) {
        DBCollection table = getDB().getCollection(tableName);
        return table.findAndModify(query, update);
    }

    /**
     * 查询分页数据.
     * @param tableName
     * @param cond
     */
    public List<DBObject> query(String tableName, DBObject cond, DBObject sort, int skip, int limit){
        List<DBObject> result = new ArrayList<DBObject>();
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond).sort(sort).skip(skip).limit(limit);
        while (cursor.hasNext()){
            result.add(cursor.next());
        }
        cursor.close();
        return result;
    }
    /**
     * 查询命中记录数.
     * @param tableName
     * @param cond
     * @return
     */
    public int count(String tableName, DBObject cond){
        int result;
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond);
        result = cursor.count();
        cursor.close();
        return result;
    }
    /**
     * 查询所有数据.
     * @param tableName
     * @param cond
     * @return
     */
    public List<DBObject> queryAll(String tableName, DBObject cond, DBObject sort){
        List<DBObject> result = new ArrayList<DBObject>();
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond).sort(sort);
        while (cursor.hasNext()){
            result.add(cursor.next());
        }
        cursor.close();
        return result;
    }

    /**
     * 查询所有数据,返回指定属性.
     * @param tableName
     * @param cond
     * @param sort
     * @param fields
     * @return
     */
    public List<DBObject> queryAll(String tableName, DBObject cond, DBObject sort, DBObject fields){
        List<DBObject> result = new ArrayList<DBObject>();
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond, fields).sort(sort);
        while (cursor.hasNext()){
            result.add(cursor.next());
        }
        cursor.close();
        return result;
    }
    /**
     * 查询分页数据, 仅返回指定属性.
     * @param tableName
     * @param cond
     */
    public List<DBObject> query(String tableName, DBObject cond, DBObject sort, DBObject fields, int skip, int limit){
        List<DBObject> result = new ArrayList<DBObject>();
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond, fields).sort(sort).skip(skip).limit(limit);
        while (cursor.hasNext()){
            result.add(cursor.next());
        }
        cursor.close();
        return result;
    }
    /**
     * 查询单条记录.
     * @param tableName
     * @param cond
     * @return
     */
    public  DBObject findOne(String tableName, DBObject cond){
        DBCollection table = getDB().getCollection(tableName);
        return table.findOne(cond);
    }

    /**
     * 查询单条记录.
     * @param tableName
     * @param cond
     * @return
     */
    public  DBObject findOne(String tableName, DBObject cond, DBObject keys){
        DBCollection table = getDB().getCollection(tableName);
        return table.findOne(cond, keys);
    }

    /**
     * 获取数据集
     * @param tableName
     * @return
     */
    public DBCollection getCollection(String tableName) {
    	return getDB().getCollection(tableName);
    }

    /**
     * 批量转换ObjectId.
     * @param id
     * @return
     */
    private ObjectId[] toObjectIds(String...id){
        ObjectId[] result = new ObjectId[id.length];
        for (int i = 0; i < id.length; i++) {
            result[i] = new ObjectId(id[i]);
        }
        return result;
    }
    /**
     * 聚集函数
     * @param tableName
     * @param list
     * @return
     */
    public CommandResult aggregate(String tableName, List<DBObject> list) {
		if (list.isEmpty()) {
			throw new RuntimeException("aggragate faild: " + tableName + " , ops args must > 0"); 
		}
		DBObject firstOp = list.get(0);
		DBObject[] additionalOps = new DBObject[list.size() - 1];
		list.subList(1, list.size()).toArray(additionalOps);
		
		AggregationOutput ouput = getCollection(tableName).aggregate(firstOp, additionalOps);
		CommandResult result = ouput.getCommandResult();
		if (result.ok()) {
			return result;
		}
		throw new RuntimeException("aggragate faild: " + tableName + " , " + result.getErrorMessage());
	}
    

    public MongoDBFactory getMongoFactory() {
        return mongoFactory;
    }

    public void setMongoFactory(MongoDBFactory mongoFactory) {
        this.mongoFactory = mongoFactory;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

	
}
