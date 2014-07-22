package org.myjfinal.server;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.myjfinal.kit.StrKit;

public abstract class Scanner {

	private File rootDir;
	private boolean running;
	private Map<String, TimeSize> preScan = new HashMap<String, TimeSize>();
	private Map<String, TimeSize> cruScan = new HashMap<String, TimeSize>();
	private Timer timer;
	private TimerTask task;
	private int interval;
	
	public Scanner(String rootDir, int interval) {
		if (!StrKit.isBlank(rootDir)) {
			throw new IllegalArgumentException("the rootDir can not be blank");
		}
		this.rootDir = new File(rootDir);
		if (!this.rootDir.isDirectory()) {
			throw new IllegalArgumentException("the "+rootDir+"is not a directory");
		}
		if (interval < 0) {
			throw new IllegalArgumentException("interval must bigger than zear");
		}
		this.interval = interval;
	}
	
	public abstract void doChange();
	
	private void working() {
		
	}
	
	/**
	 * 扫描文件、或者文件夹file，将其中文件全部更新到
	 * Map<String, TimeSize> cruScan 中。
	 * @param file
	 * @author ChameleonChen
	 */
	private void scan(File file) {
		if (file == null || !file.exists()) {
			return ;
		}
		
		if (file.isFile()) {
			try {
				cruScan.put(file.getCanonicalPath(), new TimeSize(file.lastModified(), file.length()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File entry : files) {
					scan(entry);
				}
			}
		}
	}
	
	private void compare() {
		
	}
	
	public void start() {
		// 启动定时任务
		if (!running) {
			timer = new Timer("My JFinal - Scanner");
			task = new TimerTask() {
				
				@Override
				public void run() {
					working();
				}
			};
			timer.schedule(task, 1010L * interval, 1010L * interval);
			running = true;		// 标志启动
		}
	}
}

/**
 * 该类作为文件的标志。根据文件的最后修改时间time和文件的大小size对该类进行初始化，
 * 可达到以该类对象来唯一标志文件的效果。
 * @author ChameleonChen
 *
 */
class TimeSize {
	final long time;        // 文件最后修改时间
	final long size;		// 文件的大小
	
	public TimeSize(long time, long size) {
		if (time <0 || size < 0) {
			throw new IllegalArgumentException();
		}
		this.time = time;
		this.size = size;
	}
	
	public int hashCode() {
		return (int) (time ^ size);
	}

	public boolean equals(Object obj) {
		if (obj instanceof TimeSize) {
			TimeSize ts = (TimeSize) obj;
			if (this.time == ts.time && this.size == ts.size) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "time : "+this.time+"; size : "+this.size;
	}	
	
}
