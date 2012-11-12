package com.google.code.atpfx;

import java.util.*;

import com.google.code.atpfx.data.*;
import org.hibernate.Session;
import com.google.code.db.HibernateUtil;

/*
 * This will be the internal memory representation of the data.
 * The ticks can have two sources, a signal provider, or a
 * database. If reading ticks from DB, there is no need to
 * re-save them, so for back testing or for aggregation of
 * ticks into candles we also use InternalDataFormat.
 * 
 * This is an Observable and changes due to two things:
 * 1) a new tick has arrived into the tick queue
 * 2) time has passed by and 1:n candles may be formed
 * 
 * TODO: decide if InternalDataFormat is per application, or
 * per SignalProviderType.
 * 
 * This becomes an observed class in the Observer pattern.
 */
public class InternalDataFormat extends Observable {

	private static byte NUMBER_OF_PAIRS = 30;
	private static byte NUMBER_OF_INTERVALS = 13;
	private static byte NUMBER_OF_SIGNAL_PROVIDERS = 1;
	// wanted definition: candleMatrix[pair][interval][signal provider]
	private static CandlesList[][][] candleMatrix;

	private List<Tick> ticks = new LinkedList<Tick>();
	private List<Observer> observers = new ArrayList<Observer>();
	private TimeTicking timeTicking = new TimeTicking();

    


	public InternalDataFormat() {        
		// load pairs in memory (it's a constant list of constants)
		new Pairs();
		// load the intervals (it's a constant list)
		new Intervals();
		// load signal provider types in memory
		new SignalProvidersTypes();
        
		if (NUMBER_OF_PAIRS < Pairs.getPairs().size()) {
			NUMBER_OF_PAIRS = (byte) Pairs.getPairs().size();
		}
		if (NUMBER_OF_INTERVALS < Intervals.getIntervalsList().size()) {
			NUMBER_OF_INTERVALS = (byte) Intervals.getIntervalsList().size();
		}
		if (NUMBER_OF_SIGNAL_PROVIDERS < SignalProvidersTypes
				.getSignalProvidersList().size()) {
			NUMBER_OF_SIGNAL_PROVIDERS = (byte) SignalProvidersTypes
					.getSignalProvidersList().size();
		}
		candleMatrix = new CandlesList[NUMBER_OF_PAIRS][NUMBER_OF_INTERVALS][NUMBER_OF_SIGNAL_PROVIDERS];
		for (SignalProvider sp : SignalProvidersTypes.getSignalProvidersList())
			for (Interval i : Intervals.getIntervalsList())
				for (Pair p : Pairs.getPairs()) {
					// Note: indexes in the database start from 1
					// but indexes in arrays start from 0
					// therefore we need to put .get[...]Id() -1
					// TODO: here it is incorrect, because if there is a gap in
					// the numbering of pairs or intervals all falls apart
					byte signalProviderID = (byte) sp.getId();
					signalProviderID--;
					byte pairID = (byte) p.getPairId();
					pairID--;
					byte intervalID = (byte) i.getIntervalId();
					intervalID--;
					candleMatrix[pairID][intervalID][signalProviderID] = new CandlesList(
							intervalID + 1, pairID + 1, signalProviderID + 1);
					addObserver(candleMatrix[pairID][intervalID][signalProviderID]);
				}
		timeTicking.start();
	}

	public Tick getNextTick() {
		if (ticks.size() > 0)
			return this.ticks.get(0);
		return null;
	}

	public void processAllTicks() {
		while (this.ticks.size() > 0) {
			this.setChanged();
			notifyObservers(this);
			ticks.remove(0);
			this.clearChanged();
		}
	}

	public void putTickInQueue(Tick t) {
		// the queue here refers to InternalDataFormat's member called "ticks"
		if (null != t) {
			// If tick comes from DB, like for back testing, the next step is
			// not necessary;
			// If it comes from a connection, must go to DB a.s.a.p as it is
			// referenced by many other objects.
			this.ticks.add(t);
            if(GlobalProperties.isPersistenceEnabled()) {
                saveTickToDatabase(t);
            }
			processAllTicks();
		}
	}

	private Tick saveTickToDatabase(Tick t) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		return t;
	}

	// receives "InternalDataFormat" which is Observable, or null
	public void notifyObservers(Object arg0) {
		for (Observer o : observers) {
			o.update((Observable) arg0, null);
		}
	}

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void deleteObserver(Observer o) {
		if (observers.contains(o)) {
			observers.remove(o);
		}
	}

	private class TimeTicking extends Thread {
		public void run() {
			while (true) {
				InternalDataFormat.this.setChanged();
				InternalDataFormat.this.notifyObservers(null);
				InternalDataFormat.this.clearChanged();
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
