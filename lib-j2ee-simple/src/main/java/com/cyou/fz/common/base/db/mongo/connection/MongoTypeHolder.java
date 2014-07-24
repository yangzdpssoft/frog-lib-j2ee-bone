package com.cyou.fz.common.base.db.mongo.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class MongoTypeHolder{
	private static Logger logger = LoggerFactory.getLogger(MongoTypeHolder.class);
	
	public static ThreadLocal<MongoType> t = new ThreadLocal<MongoType>();
	
	public static boolean isStart(){
		return t.get() == null ? false : true;
	}
	
	public static void init(MongoType type){
		if (logger.isInfoEnabled()) {
			logger.info("init type :"+type +",o:"+t.get());
		}
		
		MongoType msg = t.get();
		//保存第一次设置的值
		if (msg == null) {
			t.set(type);
		}
	}
	
	public static void clear(){
		if (logger.isInfoEnabled()) {
			logger.info("clear type ");
		}
		MongoType mtype = t.get();
		if (mtype != null) {
			t.remove();
		}
	}

	public static MongoType get() {
		return t.get();
	}
	
}