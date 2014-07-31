package org.myjfinal.log;

public class JdkLoggerFactory implements ILoggerFactory {

	public Logger getLogger(Class<?> clazz) {
		return new JdkLogger(clazz);
	}

	public Logger getLogger(String loggerName) {
		return new JdkLogger(loggerName);
	}

}
