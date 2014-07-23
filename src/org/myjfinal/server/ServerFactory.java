package org.myjfinal.server;

import org.myjfinal.kit.PathKit;

public class ServerFactory {

	private static final int DEFAULT_PORT = 80;
	private static final int DEFAULT_SCANINTERVALSECONDS = 5;
	
	public static IServer getServer() {
		return new JettyServer(detectWebAppDir(), DEFAULT_PORT, "/", DEFAULT_SCANINTERVALSECONDS);
	}
	
	/**
	 * @return the value of WebRoot
	 */
	private static String detectWebAppDir() {
		String rootClassPath = PathKit.getRootClassPath();
		if (rootClassPath.indexOf("\\WEB-INF\\") != -1) {
			String[] temp = rootClassPath.split("\\");
			return temp[temp.length - 3];
		}
		else if (rootClassPath.indexOf("/WEB-INF/") != -1) {
			String[] temp = rootClassPath.split("/");
			return temp[temp.length - 3];
		}
		else {
			throw new RuntimeException("the rootClassPath do not contain /WEB-INF/");
		}
	}
}
