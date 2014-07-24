package org.myjfinal.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.myjfinal.config.JFinalConfig;

public class JFinalFilter implements Filter{
	
	public JFinalConfig jfinalConfig;


	public void init(FilterConfig filterConfig) throws ServletException {
		createJFinalConfig(filterConfig.getInitParameter("configClass"));
		
	}
	
	/**
	 * @param configClass 在web.xml文件中配置.
	 * @author ChameleonChen
	 */
	public void createJFinalConfig(String configClass) {
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

}
