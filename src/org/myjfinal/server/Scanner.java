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
	
	/**
	 * 该类可用于检测rootDir目录中的文件是否被修改，
	 * 如果被修改则触发onChange()方法。
	 * @param rootDir 被检测的文件目录,不可以为空。
	 * @param interval 检测的时间间隔，需大于0.
	 * @author ChameleonChen
	 */
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
	
	/**
	 * 该方法需要被重载，以指定Scanner类检测到rootDir目录中文件被修改后所执行的操作。
	 * @author ChameleonChen
	 */
	public abstract void onChange();
	
	/**
	 * 该方法作为TimeTask,每隔一定时间执行。
	 * @author ChameleonChen
	 */
	private void working() {
		scan(rootDir);
		compare();
		
		preScan.clear();
		cruScan.putAll(cruScan);
		cruScan.clear();
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
		// 第一次执行compare()时，preScan.size() == 0
		if (preScan.size() == 0) {
			return ;
		}
		
		//TODO 学习|HashMap|的equals()的实现方法。
		if (!preScan.equals(cruScan)) {
			onChange();
		}
	}
	
	/**
	 * 
	 * @author ChameleonChen
	 */
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
 * 
 * @author ChameleonChen
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
