package test.myjfinal.kit;

import org.junit.Test;

import junit.framework.TestCase;

import org.myjfinal.kit.PathKit;

public class TestPathKit extends TestCase {

	@Test
	public void testGetWebRootPath() {
		String path = PathKit.getWebRootPath();
		System.out.println(path);
	}
}
