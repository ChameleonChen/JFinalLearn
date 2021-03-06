package org.myjfinal.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.myjfinal.config.JFinalConfig;
import org.myjfinal.log.Logger;

public class JFinalFilter implements Filter{
	
	private JFinalConfig jfinalConfig;
	private final JFinal jfinal = JFinal.me();

	private static Logger log;

	public void init(FilterConfig filterConfig) throws ServletException {
		initLogger();
		createJFinalConfig(filterConfig.getInitParameter("configClass"));
		
		if (jfinal.init(jfinalConfig, filterConfig.getServletContext()) == false) {
			throw new RuntimeException("filter init failed");
		}
	}
	
	/**
	 * @param configClass 在web.xml文件中配置.
	 * @author ChameleonChen
	 */
	private void createJFinalConfig(String configClass) {
		if (configClass == null) {
			throw new RuntimeException("please set configClass in the web.xml");
		}
		
		try {
			Object o = Class.forName(configClass).newInstance();
			if (o instanceof JFinalConfig) {
				jfinalConfig = (JFinalConfig) o;
			}
			else {
				throw new RuntimeException("Can not new instance by configClass : "+configClass);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
	}
	
	public void destroy() {
		
	}

	public static void initLogger() {
		log = Logger.getLogger(JFinalFilter.class);
	}
}
