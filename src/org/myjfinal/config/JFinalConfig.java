package org.myjfinal.config;

public abstract class JFinalConfig {

	public abstract void configConstant(Constants me);
	public abstract void configRoute(Routes me);
	public abstract void configPlugin(Plugins me);
	public abstract void configHandler(Handlers me);
	public abstract void configInterceptor(Interceptors me);
}
