package com.google.code.atpfx;

import com.google.code.atpfx.data.Pair;
import com.google.code.atpfx.data.Pairs;
import com.google.code.db.HibernateUtil;
import junit.framework.TestCase;
import org.hibernate.*;

public class MyBasicTests extends TestCase {

	// test if we can connect and disconnect
	public void testConnectionToMarket() {

		AccountLoginDetails accountLoginDetails = new AccountLoginDetails();
		boolean hasConnected = false;
		IConnection myFXCMConnection = new FXCMConnection(
				accountLoginDetails.getUserName(), accountLoginDetails
						.getPassword(), accountLoginDetails.getAccountType());
		assertFalse("The connection has not yet been initiated", hasConnected);
		myFXCMConnection.open();
		hasConnected = myFXCMConnection.isOpened();
		myFXCMConnection.close();
		assertTrue("The connection could not be established", hasConnected);
	}

	// test if we get the EUR/USD data
	// connect, wait 20 seconds, disconnect
	// then call method belonging to connection to check if we received EUR/USD
	// data
	// Note: during weekend, when market is closed, this test must fail
	public void testGetEurUsdDataFromMarket() {
		// open connection
		FXCMConnection myFXCMConnection = new FXCMConnection();
		myFXCMConnection.open();
		// wait 20 seconds
		secondsToWait(20);
		// close connection
		myFXCMConnection.close();
		//assertTrue("Did not get any EUR/USD data", myFXCMConnection.gotEurUsdData());
	}

	public void testConnectionToDB() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		new Pairs(session);
		for (Pair p : Pairs.getPairs()) {
			System.out.println(p.getPairId() + " == " + p.getPairName());
		}
		// For some reason, next line prevents Hibernate working fine further
		// HibernateUtil.getSessionFactory().close();
	}

	// opens connection to FIX server
	// opens connection to DB
	// waits 1s for messages to come, 3 times
	// inserts the ticks into TICKS table
	// closes all connections
	public void testSaveTick() {
		FXCMConnection myFXCMConnection = null;
		Session session = null;
		try {
			myFXCMConnection = new FXCMConnection();
			myFXCMConnection.open();
			// here if we got connected and we may receive messages
			// we initialize connection to DB only if we may get ticks
			// parsing the messages to get the ticks
			for (int counter = 0; counter < 10; counter++) {
				// wait a second
				secondsToWait(1);
				while (myFXCMConnection.getGenericMessagesReceived().size() > 0) {
					// as long as there are messages in the received queue
					// process them, look for ticks, and save ticks in DB
					//Tick t = myFXCMConnection.getNextTick();
					// TODO: attention! t might be null here!
//					if (null != t) {
//						// saving the tick
//						session = HibernateUtil.getSessionFactory()
//								.getCurrentSession();
//						session.beginTransaction();
//						session.save(t);
//						session.getTransaction().commit();
//						// here if we have a tick to display
//						System.out.print(Pairs.getPair(t.getPairId())
//								+ " ");
//						System.out.println(t.toString());
//
//					}
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
