package com.carson.other;

import java.util.concurrent.TimeUnit;

public class BenchResult extends Timer{
	private long hashes;
	private long blocksMined;
	private long speed;
	
	private int difficulty;
	
	public BenchResult(long blocksMined, int difficulty) {
		this.blocksMined = blocksMined;
		this.difficulty = difficulty;
		
	}
	
	public void stop(long hashes) {
		this.stop();
		this.hashes = hashes;
		try {
			this.speed = hashes / seconds;
		}catch (ArithmeticException e) {
			System.err.println("to little blocks. needs to take over one second");
		}
	}
	
	public long getDifficulty() {
		return difficulty;
	}
	public long getHashes() {
		return hashes;
	}
	public long getBlocksMined(){
		return blocksMined;
	}
	public long getTimePerBlock() {
		return TimeUnit.SECONDS.convert(nano / blocksMined, TimeUnit.NANOSECONDS);
	}
	public long getHashesPerBlock() {
		return (long)(hashes / blocksMined);
	}
	public long getHashesPerSecond() {
		return (long)(hashes / seconds);
	}
	
	
	public long getSpeedHz() {
		return speed;
	}
	public long getSpeedkHz() {
		return (long)(speed / Math.pow(10, 3));
	}
	public long getSpeedmHz() {
		return (long)(speed / Math.pow(10, 6));
	}
	public long getSpeedgHz() {
		return (long)(speed / Math.pow(10, 9));
	}
	public long getSpeedtHz() {
		return (long)(speed / Math.pow(10, 12));
	}
	
	public void printResults() {
		System.out.println("\ndifficulty:" + getDifficulty() + "\thashes:" + getHashes() + "\tblocks mined:" + getBlocksMined()
		+"\n\tseconds:" + getSeconds() + 
		"\n\tmilliseconds" + getMilliseconds() +
		"\n\tnanoseconds:" + getNano() +
		"\n\tspeed:\n-" + getSpeedHz() + " Hzs"
		+"\n-" + getSpeedkHz() + " kHz"
		+"\n-" + getSpeedmHz() + " mHz"
		+"\n-" + getSpeedtHz() + " tHz" +
		"\n time to mine a block:" + getTimePerBlock() + " seconds"
		+"\n hashes to mine a block:" + getHashesPerBlock() +
		
		"\n\n"
		
			
		
		);
	}
}
