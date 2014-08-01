package test.myjfinal.core;


import javax.servlet.ServletException;

import org.myjfinal.core.JFinalFilter;

import junit.framework.TestCase;

public class TestJFinalFilter extends TestCase {

	@Deprecated
	public void testCreateJFinalConfig() {
//		JFinalFilter filter = new JFinalFilter();
//		filter.createJFinalConfig("demo.Test");
//		filter.jfinalConfig.forTest();
	}
	
	public void testInit() {
		JFinalFilter jFinalFilter = new JFinalFilter();
		try {
			jFinalFilter.init(null);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
