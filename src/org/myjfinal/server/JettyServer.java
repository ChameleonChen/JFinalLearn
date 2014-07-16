package org.myjfinal.server;

import org.eclipse.jetty.server.Server;
import org.myjfinal.kit.PortKit;
import org.myjfinal.kit.StrKit;
public class JettyServer implements IServer {

	private int port;	// 服务器的端口
	private String context;	// 上下文
	private String webAppDir;
	private int scanIntervalSeconds;	// 扫描间隔时间
	private boolean running = false;	// 判断服务器是否运行
	private Server server;	// Jetty服务
	
	/**
	 * 配置服务器的一些参数
	 * @param webAppDir
	 * @param port
	 * @param context
	 * @param scanIntervalSeconds
	 */
	public JettyServer(String webAppDir, int port, String context, int scanIntervalSeconds) {
		
		if (webAppDir == null) {
			throw new IllegalArgumentException("Invalid webAppDir of the server :"+webAppDir);
		}
		if (port < 0 || port > 65536) {
			throw new IllegalArgumentException("Invalid port of the server :"+port);
		}
		if (StrKit.isBlank(context)) {
			throw new IllegalArgumentException("Invalid context of the server :"+context);
		}
		if (scanIntervalSeconds < 0) {
			throw new IllegalArgumentException("Invalid scanIntervalSeconds of the server :"+scanIntervalSeconds);
		}
		
		this.webAppDir = webAppDir;
		this.context = context;
		this.port = port;
		this.scanIntervalSeconds = scanIntervalSeconds;
		
	}
	
	public void start() {
		if (!running) {
			
		}
	}

	public void stop() {
		if (!running) {
			
		}
	}

	private void doStart() {
		if (!PortKit.isAvailable(port)) {
			throw new IllegalArgumentException("the port is already used :"+port);
		}
		
		
	}
	
	private void doStop() {
		
	}
	
}
