package com.google.code.atpfx.data;

import java.util.Date;

public class Candle {
	private long id; // 64 bits
	private long signalProviderId;
	// signalProviderId identifies a signal provider
	// each market maker may provide different spreads, buy and sell
	// TODO: check if we need more then 127 connection types
	private long pairId;
	// pairId assumes standard FIX names
	// the pairs are EUR/USD=1, GBP/USD=2, ..., and these constants are stored
	// in DB
	// TODO: check if we need more then 127 pairs
	private long intervalId;
	// intervalId assumes properties file or table describing the names of
	// intervals,
	// 0=tick, 1=1 minute, 2=5 minutes, ..., 7=2 hours, ..., 10=1 day, ..., 13=1
	// year
	// TODO: check if we need design for other intervals
	private Tick open;
	private Tick low;
	private Tick high;
	private Tick close;
	private long openTimestamp;

	public Candle() {
	}

	public long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(long id) {
		this.id = id;
	}

	public long getSignalProviderId() {
		return signalProviderId;
	}

	public void setSignalProviderId(long signalProviderId) {
		this.signalProviderId = signalProviderId;
	}

	public long getPairId() {
		return pairId;
	}

	public void setPairId(long pairId) {
		this.pairId = pairId;
	}

	public long getIntervalId() {
		return intervalId;
	}

	public void setIntervalId(long intervalId) {
		this.intervalId = intervalId;
	}

	public Tick getOpen() {
		return open;
	}

	public void setOpen(Tick open) {
		this.open = open;
	}

	public Tick getLow() {
		return low;
	}

	public void setLow(Tick low) {
		this.low = low;
	}

	public Tick getHigh() {
		return high;
	}

	public void setHigh(Tick high) {
		this.high = high;
	}

	public Tick getClose() {
		return close;
	}

	public void setClose(Tick close) {
		this.close = close;
	}

	public long getOpenTimestamp() {
		return openTimestamp;
	}

	public void setOpenTimestamp(long openTimestamp) {
		this.openTimestamp = openTimestamp;
	}

	public String toString() {
		String s;
		s = Pairs.getPair(this.getPairId()).getPairName() + " "
				+ Intervals.getIntervalNameById(this.getIntervalId()) + " "
				+ SignalProvidersTypes.getNameById(this.getSignalProviderId())
				+ System.getProperty("line.separator");
		if (null != this.getOpen()) {
			s = s + "   Open  " + this.open.toString()
					+ System.getProperty("line.separator");
			s = s + "   High  " + this.high.toString()
					+ System.getProperty("line.separator");
			s = s + "   Low   " + this.low.toString()
					+ System.getProperty("line.separator");
			s = s + "   Close " + this.close.toString()
					+ System.getProperty("line.separator");
		} else {
			s = s + "   No tick has come to this interval"
					+ System.getProperty("line.separator");
		}
		s = s + "   Since " + new Date(this.getOpenTimestamp());
		return s;
	}

	public void checkNewHigh(Tick t) {
		if (t.getBuy() > this.high.getBuy()) {
			high = t;
		}

	}

	public void checkNewLow(Tick t) {
		if (t.getSell() < this.low.getSell()) {
			low = t;
		}
	}
}
