package demo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;

public class DemoConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/hello", HelloController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		
	}
	
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}

}
