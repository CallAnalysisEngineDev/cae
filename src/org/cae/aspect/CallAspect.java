package org.cae.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.cae.dao.IBaseDao;

@Aspect
public class CallAspect {

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 一般来说dao层已经把预想到的异常全部处理了,但是也有可能发生未能预想到的异常
	 * 这里是把dao层没有处理到的异常都拦截下来并进行最高级别FATAL的日志记录 相当于异常处理的最后一条防线
	 * 
	 * @param jp
	 * @param ex
	 */
	@AfterThrowing(throwing = "ex", pointcut = "execution(* org.cae.dao.impl.*.*(..))")
	public void afterCallDaoException(JoinPoint jp, Exception ex) {
		Logger daoLogger = ((IBaseDao) jp.getTarget()).getLogger();
		if (daoLogger != null)
			daoLogger.fatal(ex.getMessage(), ex);
		else {
			String stackInfo = "";
			for (StackTraceElement element : ex.getStackTrace()) {
				stackInfo += element + "\n";
			}
			logger.fatal("不存在" + jp.getTarget().getClass().getName()
					+ "的logger,使用切面本身的logger,严重消息为\n" + stackInfo, ex);
		}
	}
}
