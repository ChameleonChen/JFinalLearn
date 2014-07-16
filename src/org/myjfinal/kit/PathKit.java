package org.myjfinal.kit;

import java.io.File;

public class PathKit {

	private static String webRootPath;
	
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
	
	public static void main(String[] args) {
		System.out.println(detectWebRootPath());
	}
}
