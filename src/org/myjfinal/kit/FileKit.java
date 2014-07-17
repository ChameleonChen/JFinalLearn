package org.myjfinal.kit;

import java.io.File;

public class FileKit {

	/**
	 * @param file 待删除的文件或者文件夹。
	 * 删除传入的文件或者文件夹，如果file为null，则不进行任何操作。
	 */
	public static void deletFile(File file) {
		if (file != null && file.exists()) {
			
			if (file.isFile()) {
				file.delete();
			}
			else if (file.isDirectory()) {
				/*
				 * 如果是文件夹，递归删除。
				 */
				File[] files = file.listFiles();
				for (int i=0; i < files.length; i++) {
					deletFile(files[i]);
				}
			}
			
			file.delete();
		}
	}
}
