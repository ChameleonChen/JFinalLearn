package org.myjfinal.kit;

public class StrKit {

	/**
	 * if str equals null or "", return true
	 */
	public static boolean isBlank(String str){
		return str == null || "".equals(str.trim()) ? true : false;	// 使用trim()修剪str
	}
	
}
