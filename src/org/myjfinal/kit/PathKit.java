package org.myjfinal.kit;

import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;
import java.net.URISyntaxException;

public class PathKit {

	private static String webRootPath;
	private static String rootClassPath;
	
	/**
	 * 
	 * @return .../WebRoot/WEB-INF/class
	 */
	public static String getRootClassPath() {
		if (rootClassPath == null) {
			try {
				String path = PathKit.class.getResource("/").toURI().getPath();
				return new File(path).getCanonicalPath();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rootClassPath;
	}
	
	public static String getWebRootPath() {
		if (webRootPath == null)
			webRootPath = detectWebRootPath();
		return webRootPath;
	}
	
	/**
	 * 用于初始化配置webRootPath。
	 * 返回WebRoot文件夹的路径 ...\WebRoot
	 * @return
	 */
	private static String detectWebRootPath() {
		try {
			/*
			 * getResource() 的不同参数对比：
			 * 参数一 "" ： 该class文件的路径。
			 * 参数二 "file.txt" ：相对路径寻找，与该class文件同一文件夹的file.txt文件。
			 * 参数三 "/" : 项目编译后所有class文件的根目录。
			 */
			String path = PathKit.class.getResource("/").toURI().getPath();	// getResource("/")获取class文件的根目录，此处为WebRoot/WEB-INF/classes/
			return new File(path).getParentFile().getParentFile().getCanonicalPath();	// 返回../WebRoot
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 将文件路劲转换成windows系统格式的文件路径。
	 * 使用该方法之前可以通过 boolean is = "\\".equals(File.separator);
	 * 来判断系统文件路径格式是否为windows格式。
	 * @param path
	 * @return
	 */
	public static String convertPathToWindowsFormat(String path) {
		if (path == null) throw new NullPointerException();
		
		/*
		 * 执行这段语句后，storeDir变量由存储以为'/'变成了存储两位的'\'。
		 * 因为'\'为转移字符，因此内存中必须存储两位的'\'才能显示表示'\'。
		 */
		return path.replaceAll("/", "\\\\");
	}
	
}
