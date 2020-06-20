
public class Bucket {
	
	private long size;
	private long time;
	private long prev_time;
	
	public long getPrev_time() {
		return prev_time;
	}

	public void setPrev_time(long prev_time) {
		this.prev_time = prev_time;
	}

	public Bucket(long time, long prev_time, long size) {
		this.size = size;
		this.time = time;
		this.prev_time = prev_time;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
}
