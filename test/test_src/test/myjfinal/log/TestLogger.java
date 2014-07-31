package test.myjfinal.log;

import org.myjfinal.log.ILoggerFactory;

import com.jfinal.log.JdkLoggerFactory;

import junit.framework.TestCase;

public class TestLogger extends TestCase {

	// failed
	public void testInit() {
		ILoggerFactory factory = null;
		
		try {
			Class.forName("org.apache.log4j.Logger");
			Class<?> log4jLoggerFactory = Class.forName("com.jfinal.log.Log4jLoggerFactory");
			factory = (ILoggerFactory) log4jLoggerFactory.newInstance();
		} catch(Exception e) {
			assertTrue(false);
		}
		
	}
}
