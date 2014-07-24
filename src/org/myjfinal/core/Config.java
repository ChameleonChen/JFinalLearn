package org.myjfinal.core;

import org.myjfinal.config.*;

class Config {

	/*
	 * 这些对象存储了不同的配置信息，这些类的定义在org.myjfinal.config包中。
	 */
	private static final Constants constants = new Constants();
	private static final Routes routes = new Routes();
	private static final Plugins plugins = new Plugins();
	private static final Interceptors interceptors = new Interceptors();
	private static final Handlers handlers = new Handlers();
	
	public static void configJFinal(JFinalConfig jfinalConfig) {
		//TODO 弄清楚confgi调用顺序有无要求。
		jfinalConfig.configConstant(constants);				initLoggerFactory();
		jfinalConfig.configRoute(routes);
		jfinalConfig.configPlugin(plugins);					startPlugins();	// very important!!!
		jfinalConfig.configInterceptor(interceptors);
		jfinalConfig.configHandler(handlers);
	}
	
	private static void initLoggerFactory() {
		
	}
	
	private static void startPlugins() {
		
	}
}
