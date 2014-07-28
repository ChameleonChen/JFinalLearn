package org.myjfinal.config;

import java.util.HashMap;
import java.util.Map;

import org.myjfinal.core.Controller;;

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
}
