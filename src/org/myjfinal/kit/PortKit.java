package org.myjfinal.kit;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.DatagramSocket;
public class PortKit {

	/**
	 * 检测计算机端口port是否可以使用，返回false表明端口已经被占用。
	 * 比如说某个进程占用了80端口，则当port = 80时，函数返回false。
	 * @param port
	 * @return
	 */
	public static boolean isAvailable(int port){
		if (port < 0) {
			return false;
		}
		
		ServerSocket ss = null;
		DatagramSocket ds = null;
		
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);	//ServerSocket类中，默认值为false，该方法必须在绑定端口前使用才有效
			ds = new DatagramSocket(port);
			ss.setReuseAddress(true);
			
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
				}
			}
		}
		
		return false;
	}
	
}
