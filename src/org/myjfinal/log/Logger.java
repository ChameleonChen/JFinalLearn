package org.myjfinal.log;

public abstract class Logger {

	private static ILoggerFactory factory;
	
	static {
		init();
	}
	
	private static void init() {
		if (factory != null) {
			return;
		}
		
		/*
		 *使用log4j，
		 *如果没有则用基于|Java.util.logging.Logger|实现的|JdkLogger| 和|JdkLoggerFactory|
		 */
		try {
			Class.forName("org.apache.log4j.Logger");
			Class<?> log4jLoggerFactory = Class.forName("com.jfinal.log.Log4jLoggerFactory");
			factory = (ILoggerFactory) log4jLoggerFactory.newInstance();
		} catch (Exception e) {
			factory = new JdkLoggerFactory();
		}
	}
	
	public static Logger getLogger(String loggerName) {
		return factory.getLogger(loggerName);
	}
	
	public static Logger getLogger(Class<?> clazz) {
		return factory.getLogger(clazz);
	}
	
	/*
	 * 等级由低到高：debug < info < warn < error < fatal
	 */
	
	/*输出调试信息*/
	public abstract void debug(String message);
	
	public abstract void debug(String message, Throwable throwable);
	
	/*输出提示信息*/
	public abstract void info(String message);
	
	public abstract void info(String message, Throwable throwable);
	
	/*输出警告信息*/
	public abstract void warn(String message);
	
	public abstract void warn(String message, Throwable throwable);
	
	/*输出错误信息*/
	public abstract void error(String message);
	
	public abstract void error(String message, Throwable throwable);
	
	public abstract void fatal(String message);
	
	public abstract void fatal(String message, Throwable throwable);
	
	
	public abstract boolean isDebugEnabled();

	public abstract boolean isInfoEnabled();

	public abstract boolean isWarnEnabled();

	public abstract boolean isErrorEnabled();
	
	public abstract boolean isFatalEnabled(); 
}
