package org.myjfinal.server;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.myjfinal.kit.FileKit;
import org.myjfinal.kit.PathKit;
import org.myjfinal.kit.PortKit;
import org.myjfinal.kit.StrKit;
public class JettyServer implements IServer {

	private int port;	// 服务器的端口
	private String context;	// 上下文
	private String webAppDir;
	private int scanIntervalSeconds;	// 扫描间隔时间
	private boolean running = false;	// 判断服务器是否运行
	private Server server;	// Jetty服务
	
	/*此构造方法仅用于对该类的测试*/
	public JettyServer() {	}
	
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
	
	private void deletSessionData() {
		try {
			FileKit.deletFile(new File(getStoreDir()));
		}catch(Exception e) {
		}
	}
	
	private String getStoreDir() {
		//TODO 搞清 /../../session_data/的作用
		String storeDir = PathKit.getWebRootPath() + "/../../session_data" + context;
		if ("\\".equals(File.separator)) {
			/*
			 * 执行这段语句后，storeDir变量由存储以为'/'变成了存储两位的'\'。
			 * 因为'\'为转移字符，因此内存中必须存储两位的'\'才能显示表示'\'。
			 */
			storeDir = storeDir.replaceAll("/", "\\\\");	//将原来的文件分隔符替换
		}
		return storeDir;
	}
	
	public void setContext(String context) {
		this.context = context;
	}
	
	public static void main(String[] args) {
		JettyServer jettyServer = new JettyServer();
		jettyServer.setContext("/");
//		System.out.println(File.separator+"\n"+jettyServer.getStoreDir());
		File file = new File(jettyServer.getStoreDir());
		FileKit.deletFile(file);
	}
	
}
