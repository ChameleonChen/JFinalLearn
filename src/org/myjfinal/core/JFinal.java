package org.myjfinal.core;

import org.myjfinal.server.IServer;
import org.myjfinal.server.ServerFactory;

public class JFinal {
	
	private static IServer server;

	/**
	 * 该方法作为在Eclipse集成开发环境和Jetty框架下的调试入口。
	 * @param args
	 * @author ChameleonChen
	 */
	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			server = ServerFactory.getServer();
			server.start();
		}
		else {
			String webAppDir = args[1];
			int port = Integer.parseInt(args[2]);
			String context = args[3];
			int scanIntervalSeconds = Integer.parseInt(args[4]); 
			server = ServerFactory.getServer(webAppDir, port, context, scanIntervalSeconds);
			server.start();
		}
	}
}
