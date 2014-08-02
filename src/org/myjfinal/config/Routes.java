package org.myjfinal.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.myjfinal.core.Controller;;

/**
 * Routes拥有两个分别管理|Controller|和viewPath的|HashMap|,
 * 默认情况下，两个表拥有相同的key，这使得controller和viewPath形成简介的映射关系。
 * 
 * @author ChameleonChen
 *
 */
public abstract class Routes {
	
	private final Map<String, Class<? extends Controller>> map = new HashMap<String, Class<? extends Controller>>();
	private final Map<String, String> viewPathMap = new HashMap<String, String>();
	
	private static String baseViewPath;
	
	public abstract void config();
	
	public static void setBaseViewPath(String baseViewPath) {
		if (baseViewPath == null)
			throw new NullPointerException("the baseViewPath can not be null");
		if ("".equals(baseViewPath)) {
			throw new IllegalArgumentException("the baseViewPath can not be blank");
		}
		
		baseViewPath = baseViewPath.trim();
		if (!baseViewPath.startsWith("/")) {
			baseViewPath = "/" + baseViewPath;
		}
		if (baseViewPath.endsWith("/")) {
			baseViewPath = baseViewPath.substring(0, baseViewPath.length() - 1);
		}
		
		Routes.baseViewPath = baseViewPath;
	}
	
	public Routes add(Routes routes) {
		if (routes != null) {
			this.config();    //TODO 搞清config()作用
			this.map.putAll(routes.map);
			this.viewPathMap.putAll(routes.viewPathMap);
		}
		return this;
	}
	
	/**
	 *   web请求时，通过controllerKey可以访问controller,
	 * 例如：
	 *     controllerKey         -->    controller.index()
	 *     controllerKey/method  -->    controller.method()
	 *   
	 *   controller需要对应一个viewPath，此两个分别保存在以controllerKey为
	 * key的HashMap中。
	 * 
	 * @param controllerKey
	 * @param controller
	 * @param viewPath
	 * @return
	 */
	public Routes add(String controllerKey, Class<? extends Controller> controller, String viewPath) {
		if (controllerKey == null)
			throw new NullPointerException("the controllerKey can not be null");
		if ("".equals(controllerKey))
			throw new IllegalArgumentException("the controllerKey can not be blank");
		if (controller == null)
			throw new NullPointerException("the controller can not be null");
		
		if (!controllerKey.startsWith("/")) {
			controllerKey = "/" + controllerKey;
		}
		
		if (viewPath == null || "".equals(viewPath.trim())) {
			viewPath = controllerKey;
		}else {
			viewPath = viewPath.trim();
			if (!viewPath.startsWith("/")) {
				viewPath = "/" + viewPath;
			}
			if (!viewPath.endsWith("/")) {
				viewPath = viewPath + "/";
			}
			
			if (baseViewPath != null) {
				viewPath = baseViewPath + viewPath;
			}
		}
		
		if (!map.containsKey(controllerKey)) {
			map.put(controllerKey, controller);
		}
		if (!viewPathMap.containsKey(viewPath)) {
			viewPathMap.put(controllerKey, viewPath);
		}
		
		return this;
	}
	
	public Routes add(String controllerKey, Class<? extends Controller> controller) {
		return add(controllerKey, controller, controllerKey);
	}
	
	public Set<Entry<String, Class<? extends Controller>>> getControllerEntrySet() {
		return map.entrySet();
	}
	
	public String getViewPath(String viewPathKey) {
		return viewPathMap.get(viewPathKey);
	}
}
