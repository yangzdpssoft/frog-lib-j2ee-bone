/**
 * 
 */
package com.cyou.fz.common.base.db.mongo.connection;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;

import java.util.List;

/**
 * MongoDB 连接池工厂
 * 用以初始化连接池参数
 * @author linliangyi
 * 2012-5-4
 */
public class MongoDBFactory {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MongoDBFactory.class);

	//服务器地址列表
	private List<ServerAddress> serverAddressList;
	//mongo.option.autoConnectRetry=true
	private boolean autoConnectRetry;
	//mongo.option.socketKeepAlive=true
	private boolean socketKeepAlive;
	//mongo.option.connectionsPerHost=2000
	private int connectionsPerHost;
	//mongo.option.connectTimeout=3000
	private int connectTimeout;
	//mongo.option.socketTimeout=2000
	private int socketTimeout;
	//mongo.option.maxWaitTime=5000
	private int maxWaitTime;
	//mongo.option.threadsAllowedToBlockForConnectionMultiplier=1	
	private int threadsAllowedToBlockForConnectionMultiplier;
	//MongoDB连接池实例
	private static Mongo singleton;
	
	private static Mongo secondaryMongo;
	
	public MongoDBFactory(){
		
	}

	public List<ServerAddress> getServerAddressList() {
		return serverAddressList;
	}

	public void setServerAddressList(List<ServerAddress> serverAddressList) {
		this.serverAddressList = serverAddressList;
	}

	public boolean isAutoConnectRetry() {
		return autoConnectRetry;
	}

	public void setAutoConnectRetry(boolean autoConnectRetry) {
		this.autoConnectRetry = autoConnectRetry;
	}
	
	public boolean isSocketKeepAlive() {
		return socketKeepAlive;
	}

	public void setSocketKeepAlive(boolean socketKeepAlive) {
		this.socketKeepAlive = socketKeepAlive;
	}	

	public int getConnectionsPerHost() {
		return connectionsPerHost;
	}

	public void setConnectionsPerHost(int connectionsPerHost) {
		this.connectionsPerHost = connectionsPerHost;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public int getThreadsAllowedToBlockForConnectionMultiplier() {
		return threadsAllowedToBlockForConnectionMultiplier;
	}

	public void setThreadsAllowedToBlockForConnectionMultiplier(
			int threadsAllowedToBlockForConnectionMultiplier) {
		this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}
	
	public Mongo getPrimaryMongo(){
		if (logger.isDebugEnabled()) {
			logger.debug("db in write mode!");
		}
		if(singleton == null){
			synchronized(this){
				if(singleton == null){
					MongoOptions options = new MongoOptions();
					options.autoConnectRetry = this.autoConnectRetry;
					options.socketKeepAlive = this.socketKeepAlive;
					options.connectionsPerHost = this.connectionsPerHost;
					options.connectTimeout = this.connectTimeout;
					options.socketTimeout = this.socketTimeout;
					options.maxWaitTime = this.maxWaitTime;
					options.threadsAllowedToBlockForConnectionMultiplier = this.threadsAllowedToBlockForConnectionMultiplier;					
					singleton = new Mongo(this.serverAddressList , options);					
					singleton.setReadPreference(ReadPreference.primaryPreferred());
				}
			}
		}
		return singleton;
	}
	
	public Mongo getMongo() {
		MongoType mongoType = MongoTypeHolder.get();
		if (logger.isDebugEnabled()) {
			logger.debug("mt is :"+mongoType);
		}
		if (mongoType != null) {
			switch (mongoType) {
				case READ:
					return getSecondaryMongo();
				case WRITE:
					return getPrimaryMongo();
				default:
					break;
			}
		}
		return getSecondaryMongo();
	}
	
	public Mongo getSecondaryMongo(){
		if (logger.isInfoEnabled()) {
			logger.info("db in read mode!");
		}
		if(secondaryMongo == null){
			synchronized(this){
				if(secondaryMongo == null){
					MongoOptions options = new MongoOptions();
					options.autoConnectRetry = this.autoConnectRetry;
					options.socketKeepAlive = this.socketKeepAlive;
					options.connectionsPerHost = this.connectionsPerHost;
					options.connectTimeout = this.connectTimeout;
					options.socketTimeout = this.socketTimeout;
					options.maxWaitTime = this.maxWaitTime;
					options.threadsAllowedToBlockForConnectionMultiplier = this.threadsAllowedToBlockForConnectionMultiplier;					
					secondaryMongo = new Mongo(this.serverAddressList , options);
					secondaryMongo.setReadPreference(ReadPreference.secondaryPreferred());
				}
			}
		}
		return secondaryMongo;
	}
	
	
}
