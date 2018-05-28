package com.carson.other;

import java.util.concurrent.TimeUnit;

public class Timer {
	protected long nano;
	protected long milliseconds;
	protected long seconds;
	protected long time1;
	protected long time2;
	
	
	public Timer() {
		time1 = System.nanoTime();
	}
	
	public void stop() {
		time2 = System.nanoTime();
		nano = time2 - time1;
		milliseconds = TimeUnit.MILLISECONDS.convert(nano, TimeUnit.NANOSECONDS);
		seconds = TimeUnit.SECONDS.convert(nano, TimeUnit.NANOSECONDS);
	}

	public long getNano() {
		return nano;
	}

	public long getMilliseconds() {
		return milliseconds;
	}

	public long getSeconds() {
		return seconds;
	}
}
