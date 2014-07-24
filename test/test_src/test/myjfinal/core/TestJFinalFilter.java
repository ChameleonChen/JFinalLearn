package test.myjfinal.core;

import org.myjfinal.core.JFinalFilter;

import junit.framework.TestCase;

public class TestJFinalFilter extends TestCase {

	public void testCreateJFinalConfig() {
		JFinalFilter filter = new JFinalFilter();
		filter.createJFinalConfig("demo.Test");
		filter.jfinalConfig.forTest();
	}
}
