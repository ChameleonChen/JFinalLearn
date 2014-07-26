package org.myjfinal.config;

import java.util.HashMap;
import java.util.Map;
import org.myjfinal.core.Controller;;

public abstract class Routes {
	
	private final Map<String, Class<? extends Controller>> map = new HashMap<String, Class<? extends Controller>>();
	private final Map<String, String> viewPathMap = new HashMap<String, String>();
	
	
	public abstract void config();
	
	public Routes add(Routes routes) {
		if (routes != null) {
			this.config();    //TODO 弄清楚到底config什么。
			this.map.putAll(routes.map);
			this.viewPathMap.putAll(routes.viewPathMap);
		}
		return this;
	}
	
	public Routes add(String controllerKey, Class<? extends Controller> controller, String viewPath) {
		
	}
}
