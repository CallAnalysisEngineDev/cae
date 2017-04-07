package org.cae.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.cae.dao.ICallDao;
import org.cae.dao.ISongDao;

@Aspect
public class CallAspect {
	
	private Logger logger=Logger.getLogger(this.getClass());
	
	@Before("execution(* org.cae.*.*.*.*(..))")
	public void beforeCallDao(JoinPoint jp){
		logger.info("执行["+jp.getTarget().getClass().getSimpleName()+"."+jp.getSignature().getName()+"]方法");
	}
	
	/**
	 * 一般来说dao层已经把预想到的异常全部处理了,但是也有可能发生未能预想到的异常
	 * 这里是把dao层没有处理到的异常都拦截下来并进行最高级别FATAL的日志记录
	 * 相当于异常处理的最后一条防线
	 * @param jp
	 * @param ex
	 */
	@AfterThrowing(throwing="ex",pointcut="execution(* org.cae.dao.impl.*.*(..))")
	public void afterCallDaoException(JoinPoint jp,Exception ex){
		Object dao=jp.getTarget();
		Logger daoLogger=null;
		if(dao instanceof ICallDao){
			daoLogger=((ICallDao)dao).getLogger();
		}
		else if(dao instanceof ISongDao){
			daoLogger=((ISongDao)dao).getLogger();
		}
		if(daoLogger!=null)
			daoLogger.fatal(ex.getMessage());
		else
			logger.fatal("不存在"+dao.getClass().getName()+"的logger,使用切面本身的logger,严重消息为"+ex.getMessage());
	}
}
