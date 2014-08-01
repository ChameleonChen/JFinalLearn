package org.myjfinal.core;

import java.lang.reflect.Method;

import org.myjfinal.aop.Interceptor;

import com.jfinal.core.Controller;

/**
 * |Controller|中的一个public无参方法就是一个Action.
 * Action包含的主要参数有：
 *     controllerClass
 *     controllerKey
 *     actionKey
 *     method
 *     methodName
 *     interceptors
 *     viewPath
 * 以上参数在构造函数中赋值，并且可以通过get方法获取。
 * 
 * @author ChameleonChen
 *
 */
class Action {

	private final Class<? extends Controller> controllerClass;
	private final String controllerKey;
	private final String actionKey;
	private final Method method;
	private final String methodName;
	private final Interceptor[] interceptors;
	private final String viewPath;
	
	public Action(String controllerKey, String actionKey, Class<? extends Controller> controllerClass, Method method, String methodName, Interceptor[] interceptors, String viewPath) {
		this.controllerKey = controllerKey;
		this.actionKey = actionKey;
		this.controllerClass = controllerClass;
		this.method = method;
		this.methodName = methodName;
		this.interceptors = interceptors;
		this.viewPath = viewPath;
	}
	
	public Class<? extends Controller> getControllerClass() {
		return controllerClass;
	}
	
	public String getControllerKey() {
		return controllerKey;
	}
	
	public String getActionKey() {
		return actionKey;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public Interceptor[] getInterceptors() {
		return interceptors;
	}
	
	public String getViewPath() {
		return viewPath;
	}
	
	public String getMethodName() {
		return methodName;
	}
}
