package org.myjfinal.server;

public abstract class Scanner {

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
