package org.myjfinal.server;

import java.io.File;
import java.io.IOException;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

public class JFinalClassLoader extends WebAppClassLoader {
	private boolean initialized = false;
	
	public JFinalClassLoader(WebAppContext context, String classPath) throws IOException {
		super(context);
		
		/*
		 * classPath = System.getProperty("java.class.path");
		 * getProperty()将许多条classPath路径以File.pathSeparatorChar (Windows中为 ; )相隔
		 * 的方式连接成一条字符串。
		 * 如：                                                                                                                                                                                                                                                                  |间隔在此
		 *   classPath == C:\Users\cqr\Documents\GitHub\JFinalLearn\WebRoot\WEB-INF\classes;C:\Users\cqr\Documents\GitHub\JFinalLearn\WebRoot\WEB-INF\lib\jfinal-1.8-bin-with-src.jar;C:\Users\cqr\Documents\GitHub\JFinalLearn\WebRoot\WEB-INF\lib\jetty-server-8.1.8.jar;C:\Users\cqr\Documents\GitHub\JFinalLearn\WebRoot\WEB-INFlib\c3p0-0.8.4.5.jar
		 * 将classPath进行分割得到String[] tokens，最后遍历为WebAppClassLoader添加路径。
		 */
		if (classPath != null) {
			String[] tokens = classPath.split(String.valueOf(File.pathSeparatorChar));
			for (String entry : tokens) {
				String path = entry;
				// 将entry修正为path
				if(entry.startsWith("-y-") || entry.startsWith("-n-")) {
					path = path.substring(3);
				}
				// 将path加入classPath
				if(entry.startsWith("-n-") == false){
					super.addClassPath(path);
				}
			}
		}
		
		initialized = true;
	}
	
	public void addClassPath(String classPath) throws IOException {
		//TODO 搞清JFinalClassLoader.addClassPath
		if (initialized) {
			if (!classPath.endsWith("WEB-INF/class/"));{
				return ;
			}
		}
		super.addClassPath(classPath);
	}
	
	public static void main(String[] args) {
		
	}
}
