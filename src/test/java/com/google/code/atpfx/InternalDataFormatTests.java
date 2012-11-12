package com.google.code.atpfx;

import java.util.Date;

import com.google.code.atpfx.data.Intervals;
import com.google.code.db.HibernateUtil;
import com.google.code.atpfx.data.Tick;

import junit.framework.TestCase;

public class InternalDataFormatTests extends TestCase {
	
	public void testIntervalStart() {
		// InternalDataFormat is needed to load intervals from DB
		@SuppressWarnings("unused")
		InternalDataFormat internalDataFormat = new InternalDataFormat();
		for (int intervalId = 1; intervalId <= 14; intervalId++) {
			Date d = new Date();//now
			Date startInterval = new Date(Intervals.getIntervalStart(d
					.getTime(), intervalId));
			System.out.print(startInterval);
			if (!Intervals.getIntervalNameById(intervalId).equalsIgnoreCase(""))
				System.out.println(" - "
						+ Intervals.getIntervalNameById(intervalId));
			else
				System.out.println(" - current time");
		}
	}
	
	public void testCanCreateAndDisplayEmptyTick(){
		System.out.println(new Tick());
	}

	public void testCanCreateAndDisplayEmptyCandles(){
		InternalDataFormat internalDataFormat = new InternalDataFormat();
		System.out.println("Waiting one minute without sending new ticks...");
		secondsToWait(60); 
		//during this waiting period one thread is notifying 
		//the InternalDataFormat that time has passed by
		internalDataFormat.processAllTicks(); //this should not throw errors
	}
	
	public void testInternalDataFormat() {
		InternalDataFormat internalDataFormat = new InternalDataFormat();
		FXCMConnection myFXCMConnection = null;
		try {
			myFXCMConnection = new FXCMConnection();
			myFXCMConnection.open();
			for (int counter = 60; counter > 0; counter--) {
				secondsToWait(1);
				while (myFXCMConnection.getGenericMessagesReceived().size() > 0) {
					// as long as there are messages in the received queue
					// process them, look for ticks, and save ticks in DB
					//Tick t = myFXCMConnection.getNextTick();
					// TODO: attention! t might be null here!
					//internalDataFormat.putTickInQueue(t);
					// internalDataFormat.processAllTicks();
				}
			}
		} finally {
			if (null != myFXCMConnection) {
				if (myFXCMConnection.isOpened()) {
					myFXCMConnection.close();
				}
			}
		}

	}

	// This test must execute as last test to close the session
	// it's like "finalize", needs to execute as last test
	// If I try to close the session it seems I cannot reopen it
	public void testFinalize() {
		HibernateUtil.getSessionFactory().close();
	}

	// utility method
	public void secondsToWait(int i) {
		try {
			Thread.sleep(1000 * i);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
