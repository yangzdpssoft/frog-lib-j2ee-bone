package com.cyou.fz.common.base.db.mongo.dao;


import com.cyou.fz.common.base.db.mongo.connection.MongoDBConnectionConfig;
import com.cyou.fz.common.base.db.mongo.constants.DBFieldConstant;
import com.cyou.fz.common.base.db.mongo.util.MongoUtil;
import com.cyou.fz.common.base.exception.UnCaughtException;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>mongo数据库操作</p>
 * <p>Description: </p>
 * <p>Company: cyou</p>
 *
 * @author yangz
 * @version V1.0
 * @date 2013-7-1
 */
public class MongoDAO implements InitializingBean{

    private MongoDBConnectionConfig config;

    private DB db;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // 构建连接池参数
            MongoClientOptions.Builder options = new MongoClientOptions.Builder();
            // 每个主机最大连接数
            options.connectionsPerHost(config.getConnectionsPerHost());
            // 阻塞时，最大等待连接线程数
            options.threadsAllowedToBlockForConnectionMultiplier(config.getThreadsAllowedToBlockForConnectionMultiplier());
            // 阻塞时，最大等待时间
            options.maxWaitTime(config.getMaxWaitTime());
            // 连接超时时间
            options.connectTimeout(config.getConnectTimeout());
            // socket超时
            options.socketTimeout(config.getSocketTimeout());
            /*
             * 是否socket保持活动 #true:假如链接不能建立时,驱动将重试相同的server,有最大的重试次数,默认为15次,
             * 这样可以避免一些server因为一些阻塞操作零时down而驱动抛出异常
             * ,这个对平滑过度到一个新的master,也是很有用的,注意:当集群为复制集时
             * ,驱动将在这段时间里,尝试链接到旧的master上,而不会马上链接到新master上
             * #false:当在进行socket读写时,不会阻止异常抛出,驱动已经有自动重建破坏链接和重试读操作. 推荐配置false
             */
            options.socketKeepAlive(config.getSocketKeepAlive());
            // 是否启动自动终结游标
            options.cursorFinalizerEnabled(config.isCursorFinalizerEnabled());
            // 是否使用本地MBeans和jvm版本,2.11.0驱动以后的配置
            // options.alwaysUseMBeans(false);
            // 读写分类规则
            options.readPreference(config.getReadPreference());
            // 写操作的相关配置
            options.writeConcern(config.getWriteConcern());
            ServerAddress serverAddress = new ServerAddress(config.getHost(), config.getPort());
            List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
            credentialsList.add(MongoCredential.createMongoCRCredential(config.getUsername(), config.getDbname(), config.getPassword().toCharArray()));
            MongoClient client = new MongoClient(serverAddress, credentialsList, options.build());
            db = client.getDB(config.getDbname());
        } catch (Exception e) {
            throw new UnCaughtException(e);
        }
    }

    public DB getDB() {
        return db;
    }

    /**
     * 保存和更新.
     *
     * @param tableName
     * @param dbObj
     * @return
     */
    public WriteResult saveOrUpdate(String tableName, DBObject dbObj) {
        MongoUtil.markFK(this, dbObj);
        DBCollection table = this.getDB().getCollection(tableName);
        return table.save(dbObj);
    }

    /**
     * 获取单条记录.
     *
     * @param tableName
     * @param id
     * @return
     */
    public DBObject get(String tableName, String id) {
        DBCollection table = this.getDB().getCollection(tableName);
        return table.findOne(new BasicDBObject(DBFieldConstant._ID, new ObjectId(id)));
    }

    /**
     * 获取数据部分属性.
     *
     * @param tableName
     * @param id
     * @param fields
     * @return
     */
    public DBObject get(String tableName, String id, DBObject fields) {
        DBCollection table = getDB().getCollection(tableName);
        return table.findOne(new BasicDBObject(DBFieldConstant._ID, new ObjectId(id)), fields);
    }

    /**
     * 删除记录.
     *
     * @param id
     */
    public WriteResult delete(String tableName, String... id) {
        if(id != null){
            for(String i : id){
                MongoUtil.removeMarkFK(this, get(tableName, i));
            }
        }
        ObjectId[] oids = toObjectIds(id);
        QueryBuilder queryBuilder = QueryBuilder.start(DBFieldConstant._ID);
        DBObject dbObj = queryBuilder.in(oids).get();
        DBCollection table = this.getDB().getCollection(tableName);
        WriteResult result = table.remove(dbObj);
        return result;
    }

    /**
     * 更新部分属性值.
     *
     * @param tableName
     * @param value
     * @param id
     * @return
     */

    public WriteResult update(String tableName, DBObject value, String... id) {
        QueryBuilder queryBuilder = QueryBuilder.start(DBFieldConstant._ID);
        DBObject dbObj = queryBuilder.in(toObjectIds(id)).get();
        DBObject set = new BasicDBObject("$set", value);
        WriteResult result = getDB().getCollection(tableName).updateMulti(dbObj, set);
        return result;
    }

    /**
     * 计数器.
     * @param tableName
     * @param id
     * @param field
     * @param value
     * @return
     */
    public WriteResult inc(String tableName, String id, String field, long value) {
        QueryBuilder queryBuilder = QueryBuilder.start(DBFieldConstant._ID);
        DBObject dbObj = queryBuilder.is(MongoUtil.toObjectId(id)).get();
        DBObject inc = new BasicDBObject("$inc", new BasicDBObject(field, value));
        WriteResult result = getDB().getCollection(tableName).update(dbObj, inc);
        return result;
    }

    /**
     * 批量更新.
     *
     * @param query
     * @param update
     */
    public WriteResult updateMuti(String tableName, DBObject query, DBObject update) {
        return getDB().getCollection(tableName).updateMulti(query, update);
    }

    /**
     * 查询并更新.
     *
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
     *
     * @param tableName
     * @param cond
     */
    public List<DBObject> query(String tableName, DBObject cond, DBObject sort, int skip, int limit) {
        List<DBObject> result = new ArrayList<DBObject>();
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond).sort(sort).skip(skip).limit(limit);
        while (cursor.hasNext()) {
            result.add(cursor.next());
        }
        cursor.close();
        return result;
    }

    /**
     * 查询命中记录数.
     *
     * @param tableName
     * @param cond
     * @return
     */
    public int count(String tableName, DBObject cond) {
        int result;
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond);
        result = cursor.count();
        cursor.close();
        return result;
    }

    /**
     * 查询所有数据.
     *
     * @param tableName
     * @param cond
     * @return
     */
    public List<DBObject> queryAll(String tableName, DBObject cond, DBObject sort) {
        List<DBObject> result = new ArrayList<DBObject>();
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond).sort(sort);
        while (cursor.hasNext()) {
            result.add(cursor.next());
        }
        cursor.close();
        return result;
    }

    /**
     * 查询所有数据,返回指定属性.
     *
     * @param tableName
     * @param cond
     * @param sort
     * @param fields
     * @return
     */
    public List<DBObject> queryAll(String tableName, DBObject cond, DBObject sort, DBObject fields) {
        List<DBObject> result = new ArrayList<DBObject>();
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond, fields).sort(sort);
        while (cursor.hasNext()) {
            result.add(cursor.next());
        }
        cursor.close();
        return result;
    }

    /**
     * 查询分页数据, 仅返回指定属性.
     *
     * @param tableName
     * @param cond
     */
    public List<DBObject> query(String tableName, DBObject cond, DBObject sort, DBObject fields, int skip, int limit) {
        List<DBObject> result = new ArrayList<DBObject>();
        DBCollection table = getDB().getCollection(tableName);
        DBCursor cursor = table.find(cond, fields).sort(sort).skip(skip).limit(limit);
        while (cursor.hasNext()) {
            result.add(cursor.next());
        }
        cursor.close();
        return result;
    }

    /**
     * 查询单条记录.
     *
     * @param tableName
     * @param cond
     * @return
     */
    public DBObject findOne(String tableName, DBObject cond) {
        DBCollection table = getDB().getCollection(tableName);
        return table.findOne(cond);
    }

    /**
     * 查询单条记录.
     *
     * @param tableName
     * @param cond
     * @return
     */
    public DBObject findOne(String tableName, DBObject cond, DBObject keys) {
        DBCollection table = getDB().getCollection(tableName);
        return table.findOne(cond, keys);
    }

    /**
     * 获取数据集
     *
     * @param tableName
     * @return
     */
    public DBCollection getCollection(String tableName) {
        return getDB().getCollection(tableName);
    }

    /**
     * 批量转换ObjectId.
     *
     * @param id
     * @return
     */
    private ObjectId[] toObjectIds(String... id) {
        ObjectId[] result = new ObjectId[id.length];
        for (int i = 0; i < id.length; i++) {
            result[i] = new ObjectId(id[i]);
        }
        return result;
    }

    public MongoDBConnectionConfig getConfig() {
        return config;
    }

    public void setConfig(MongoDBConnectionConfig config) {
        this.config = config;
    }
}
