package com.cyou.fz.common.base.db.mongo.connection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
/**
   *  用于切换MONGO数据源
   * 
 * @author wangj
   * 2013-3-19-下午2:34:19
 */
public class DBModeAspect {
	private static ThreadLocal<Integer> transcationNum = new  ThreadLocal<Integer>();
	private static final int STEP = 1;
	private Logger logger = LoggerFactory.getLogger(DBModeAspect.class);
	
	
	public Object performance(ProceedingJoinPoint point) throws Throwable {
		StopWatch watch = new StopWatch();
		watch.start();
		if (get() == 0){
			if (logger.isInfoEnabled()) {
				logger.info("--------------------begin tran  ---------------------------------");
			}
			
			MongoTypeHolder.init( getType(point) );
		}
		Object obj = null;
		try {
			incrementAndGet();
			obj = point.proceed();
			return obj;
		}finally{ 
			decrementAndGet();
			
			if (get() == 0) {
				MongoTypeHolder.clear();
				watch.stop();
				logger.info("[pt in method "+point.getSignature().getName()+"]"+watch.toString());
			}
		}
	}
	
	public MongoType getType (ProceedingJoinPoint point) {
		MethodSignature ms =  (MethodSignature) point.getSignature();
		Method method = ms.getMethod();
		ReReadEnable dbMode = method.getAnnotation(ReReadEnable.class);
		if (dbMode != null) {
			return dbMode.value();
		}
		return null;
	}
	
	public Integer get(){
		Integer num = transcationNum.get();
		if (num == null) {
			num = 0;
			transcationNum.set(num);
		}
		return num;
	}
	
	public Integer incrementAndGet(){
		Integer num = get() + STEP;
		transcationNum.set(num );
		return num;
	}
	
	public Integer decrementAndGet(){
		Integer num = get()  - STEP;
		transcationNum.set(num);
		return num;
	}
}