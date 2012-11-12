package com.google.code.atpfx.data;

public class Interval {
	long intervalId;
	String intervalName;
	int intervalMinutes;
	
	public Interval(){}

	public long getIntervalId() {
		return intervalId;
	}

	@SuppressWarnings("unused")
	private void setIntervalId(long intervalId) {
		this.intervalId = intervalId;
	}

	public String getIntervalName() {
		return intervalName;
	}

	public void setIntervalName(String intervalName) {
		this.intervalName = intervalName;
	}

	public int getIntervalMinutes() {
		return intervalMinutes;
	}

	public void setIntervalMinutes(int intervalMinutes) {
		this.intervalMinutes = intervalMinutes;
	}
	
}
