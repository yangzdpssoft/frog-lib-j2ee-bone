package com.cyou.fz.common.base.db.mongo.connection;


import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
/**
 * mongodb 连接配置.
 * @author yangz
 * @date 2014/7/24
 *
 */

public class MongoDBConnectionConfig {
    /**
     * 主机.
     */
    private String host;
    /**
     * 端口.
     */
    private Integer port;
    /**
     * 数据库.
     */
    private String dbname;
    /**
     * 用户名.
     */
    private String username;
    /**
     * 密码.
     */
    private String password;
    /**
     * 每个主机最大连接数.
     */
    private Integer connectionsPerHost = 100;
    /**
     * 阻塞时，最大等待连接线程数.
     */
    private Integer threadsAllowedToBlockForConnectionMultiplier = 5;
    /**
     * 阻塞时，最大等待时间.
     */
    private Integer maxWaitTime = 1000 * 60 * 2;
    /**
     * 连接超时时间.
     */
    private Integer connectTimeout = 1000 * 10;
    /**
     * 是否启动自动终结游标.
     */
    private boolean cursorFinalizerEnabled = true;
    /**
     * socket超时.
     */
    private Integer socketTimeout = 0;
    /**
     * 是否不允许socket无限重试.
     */
    private Boolean socketKeepAlive = false;
    /**
     * 集群读取策略.
     */
    private ReadPreference readPreference = ReadPreference.primary();
    /**
     * 写操作级别，默认设置等待写入磁盘.
     */
    private WriteConcern writeConcern = WriteConcern.FSYNCED;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(Integer connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public Integer getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(
            Integer threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Boolean getSocketKeepAlive() {
        return socketKeepAlive;
    }

    public void setSocketKeepAlive(Boolean socketKeepAlive) {
        this.socketKeepAlive = socketKeepAlive;
    }

    public ReadPreference getReadPreference() {
        return readPreference;
    }

    public void setReadPreference(ReadPreference readPreference) {
        this.readPreference = readPreference;
    }

    public WriteConcern getWriteConcern() {
        return writeConcern;
    }

    public void setWriteConcern(WriteConcern writeConcern) {
        this.writeConcern = writeConcern;
    }

    public boolean isCursorFinalizerEnabled() {
        return cursorFinalizerEnabled;
    }

    public void setCursorFinalizerEnabled(boolean cursorFinalizerEnabled) {
        this.cursorFinalizerEnabled = cursorFinalizerEnabled;
    }
}
