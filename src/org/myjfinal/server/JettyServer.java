package org.myjfinal.server;

import java.io.File;
import java.io.IOException;

import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.myjfinal.core.Const;
import org.myjfinal.kit.FileKit;
import org.myjfinal.kit.PathKit;
import org.myjfinal.kit.PortKit;
import org.myjfinal.kit.StrKit;

public class JettyServer implements IServer {

	private int port;	                // 服务器的端口
	private String context;	            // 上下文
	private int scanIntervalSeconds;	// 扫描间隔时间
	private boolean running = false;	// 判断服务器是否运行
	private Server server;	            // Jetty服务
	private WebAppContext webApp;
	private String webAppDir;
	
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
		
		deletSessionData();
		/*
		 * |Server|连接 |Connector|与|Handler|
		 * |---------------------------------------------------------------------|
		 * |    ---------------          ----------            --------------    |
		 * |   |   Connector   | <----> |  Server  | <----->  |   Handler    |   |
		 * |    ---------------          ----------            --------------    |
		 * |---------------------------------------------------------------------|
		 */
		
		System.out.println("Starting JFinal :" + Const.JFINAL_VERSION);
		/*
		 * 一、概述
		 *     在Jetty框架中，Server的对象必须配合Connectpr和Handle一起使用。
		 * 也就是说Server是Connector和Handle之间的桥梁。
		 * 二、实现方案
		 *     1.实例化Server对象。
		 *     2.Connector为接口，实例化性能比较好的SelectChaanelConnector类来获取接口。
		 *       并且将Connect接口配置给Server。
		 *     3.
		 */
		server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		server.addConnector(connector);
		
		webApp = new WebAppContext();	        // 一个WebAppContext代表一个应用程序，可以是war包或者目录。
		webApp.setResourceBase(webAppDir);      // 为应用程序配置目录，该目录的地位为WebRoot
		//webApp.setWar(war);                   // 配置warb包
		webApp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
		webApp.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");	// webApp.setInitParams(Collections.singletonMap("org.mortbay.jetty.servlet.Default.useFileMappedBuffer", "false"));
		persistSession(webApp);
		
		server.setHandler(webApp);              // 将webApp作为Handle配置给server
		changeClassLoader(webApp);
		
		if (scanIntervalSeconds > 0) {
			
		}
	}
	
	private void doStop() {
		
	}
	
	private void changeClassLoader(WebAppContext webApp) {
		try {
			String classPath = System.getProperty("java.class.path");
			JFinalClassLoader wacl = new JFinalClassLoader(webApp, classPath);
			wacl.addClassPath(classPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	private void persistSession(WebAppContext webApp) {
		String storeDir = getStoreDir();
		
		//TODO 搞清为什么一定要HashSessionManager
		SessionManager sm = webApp.getSessionHandler().getSessionManager();
		if (sm instanceof HashSessionManager) {
			((HashSessionManager)sm).setStoreDirectory(new File(storeDir));
			return ;
		}
		
		HashSessionManager hsm = new HashSessionManager();
		hsm.setStoreDirectory(new File(storeDir));
		SessionHandler sh = new SessionHandler();
		sh.setSessionManager(hsm);
		webApp.setSessionHandler(sh);
	}
	
	public void setContext(String context) {
		this.context = context;
	}
	
	public static void main(String[] args) {
		JettyServer jettyServer = new JettyServer();
		jettyServer.setContext("/");
//		System.out.println(File.separator+"\n"+jettyServer.getStoreDir());
//		File file = new File(jettyServer.getStoreDir());
//		FileKit.deletFile(file);
	}
	
}
