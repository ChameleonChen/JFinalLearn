package org.myjfinal.core;

import java.util.List;

import org.myjfinal.config.*;
import org.myjfinal.log.Logger;
import org.myjfinal.plugin.IPlugin;
import org.myjfinal.plugin.activerecord.ActiveRecordPlugin;

class Config {

	/*
	 * 这些对象存储了不同的配置信息，这些类的定义在org.myjfinal.config包中。
	 */
	private static final Constants constants = new Constants();
	private static final Routes routes = new Routes(){public void config() {} };
	private static final Plugins plugins = new Plugins();
	private static final Interceptors interceptors = new Interceptors();
	private static final Handlers handlers = new Handlers();
	
	private static Logger log;
	
	public static Constants getConstants() {
		return constants;
	}
	
	public static Routes getRoutes() {
		return routes;
	}
	
	public static Plugins getPlugins() {
		return plugins;
	}
	
	public static Interceptors getInterceptors() {
		return interceptors;
	}
	
	public static Handlers getHandlers() {
		return handlers;
	}
	
	public static void configJFinal(JFinalConfig jfinalConfig) {
		//TODO 弄清楚confgi调用顺序有无要求。
		jfinalConfig.configConstant(constants);				initLoggerFactory();
		jfinalConfig.configRoute(routes);
		jfinalConfig.configPlugin(plugins);					startPlugins();	// very important!!!
		jfinalConfig.configInterceptor(interceptors);
		jfinalConfig.configHandler(handlers);
	}
	
	private static void initLoggerFactory() {
		log = Logger.getLogger(Config.class);
	}
	
	private static void startPlugins() {
		List<IPlugin> pluginList = plugins.getPluginList();
		for (IPlugin plugin : pluginList) {
			try {
				// 
				if (plugin instanceof org.myjfinal.plugin.activerecord.ActiveRecordPlugin) {
					org.myjfinal.plugin.activerecord.ActiveRecordPlugin arp = (ActiveRecordPlugin) plugin;
					if (arp.getDevMode() == null) {
						arp.setDevMode(constants.getDevMode());
					}
				}
				
				boolean success = plugin.start();
				if (!success) {
					String message = "Plugin start error :" + plugin.getClass().getName();
					log.error(message);
					throw new RuntimeException(message);
				}
			} catch(Exception e) {
				String message = "Plugin start error: " + plugin.getClass().getName() + ". \n" + e.getMessage();
				log.error(message, e);
				throw new RuntimeException(message, e);
			}
		}
	}
}
