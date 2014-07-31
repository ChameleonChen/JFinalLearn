package org.myjfinal.log;

public interface ILoggerFactory {

	public Logger getLogger(Class<?> clazz);
	
	public Logger getLogger(String loggerName);
}
