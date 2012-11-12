package com.google.code.atpfx;

import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import com.google.code.atpfx.data.Intervals;
import org.hibernate.classic.Session;

import com.google.code.db.HibernateUtil;
import com.google.code.atpfx.data.Tick;
import com.google.code.atpfx.data.Candle;

public class CandlesList implements Observer {

	private long interval;
	private long pair;
	private long signalProvider;

	private LinkedList<Candle> candles = new LinkedList<Candle>();
	private Tick latestTick;

	public CandlesList(long interval, long pair, long signalProvider) {
		this.interval = interval;
		this.pair = pair;
		this.signalProvider = signalProvider;
		this.latestTick = new Tick();
	}

	@Override
	/*
	 * receive internalDataFormat and session
	 * 
	 * There could be two kinds of events updating the CadlesList: 1) a new tick
	 * has arrived having current candlesList's pair and interval 2) time has
	 * passed and a new candle may be formed
	 */
	public void update(Observable arg0, Object arg1) {
		if (null == arg0) {
			/*
			 * here only when there is a request to make candles based on time
			 * event: the clock has moved one minute, so there are new candles
			 * to produce at least on the 1 minute interval, but there is not
			 * yet a tick for each pair
			 */
			if (checkNeedToOpenCandle())
				makeNewCandle(null); //this.save(makeNewCandle(null), arg1);
			return;
		}
		Tick t = ((InternalDataFormat) arg0).getNextTick();
		// check if the Tick received in arg1 belongs to latest candle
		if (!tickBelongsToThisCandleList(t))
			return;
		if (latestTick.getTimestamp() < t.getTimestamp())
			latestTick = t;
		// traverse list of candles in memory to find the one to which this tick
		// belongs
		// check if t belongs to a candle already in memory
		Candle c = getCandleToWhichTickBelongs(t);
		if (null == c) {
			// here when tick triggers a new candle open
			c = makeNewCandle(t);// this adds to the "candles" list as well
		} else {
			// here tick belongs to candle in variable c,
			// but open, high, low, close are not updated
			c.checkNewHigh(t);
			c.checkNewLow(t);
			c.setClose(t);
		}
		System.out.println("Candles list size: " + candles.size());
		System.out.println("Last candle: " + c);
		if (null != t) 
			System.out.println("Last tick " + t.getId());
		this.saveOrUpdate(c, arg1);

	}

	/*
	 * for each tick in the candle (open, high, low, close) get the ID from the
	 * database, or put 0 if tick is null
	 */
	private void saveOrUpdate(Candle c, Object arg1) {
		/* if candle not saved yet, save it
		 * if already saved, update it
		 * */
        if(GlobalProperties.isPersistenceEnabled()) {
            Session session = (Session) arg1;
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(c);
            session.getTransaction().commit();
        }
	}

	private boolean tickBelongsToThisCandleList(Tick t) {
		if (t.getPairId() == this.getPair()
				&& t.getSignalProviderId() == this.getSignalProvider()) {
			return true;
		}
		return false;
	}

	// expect a candle with no nulls when exiting
	private Candle getCandleToWhichTickBelongs(Tick t) {
		for (Candle c : candles) {
			if ((c.getOpenTimestamp() <= t.getTimestamp())
					&& (t.getTimestamp() - c.getOpenTimestamp() < Intervals
							.getIntervalMinutesById(this.getInterval()) * 60 * 1000)) {
				// here if we got an already opened candle where t needs to
				// update
				if (null == c.getOpen()) {
					// here if candle opened not due to tick, but due to time
					// event
					c.setOpen(t);
					c.setHigh(t);
					c.setLow(t);
					c.setClose(t);
				}
				return c;
			}
		}
		return null;
	}

	// this is only for time events, not for new ticks
	private boolean checkNeedToOpenCandle() {
		// if there is no candle, return need to create one immediately
		if (this.candles.size() == 0)
			return true;
		Long timeStamp = new Date().getTime();
		Candle c = candles.getLast();
		if (0 < Intervals.getIntervalStart(timeStamp, this.getInterval())
				- c.getOpenTimestamp()) {
			return true;
		}
		return false;
	}

	// this member may be called with a null parameter when it's a time event
	private Candle makeNewCandle(Tick t) {
		Candle c = new Candle();
		c.setIntervalId(this.getInterval());
		c.setPairId(this.getPair());
		c.setSignalProviderId(this.signalProvider);
		c.setOpen(t);
		c.setHigh(t);
		c.setLow(t);
		c.setClose(t);
		if (null != t)
			c.setOpenTimestamp(Intervals.getIntervalStart(t.getTimestamp(),
					this.getInterval()));
		else
			c.setOpenTimestamp(Intervals.getIntervalStart(new Date().getTime(),
					this.getInterval()));
		candles.add(c);
		return c;
	}

	public long getInterval() {
		return interval;
	}

	public long getPair() {
		return pair;
	}

	public long getSignalProvider() {
		return signalProvider;
	}

}
