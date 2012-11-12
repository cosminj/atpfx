/*
 * Copyright (c) 2011. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.google.code.atpfx;

import com.google.code.atpfx.base.PrintEurUsdMarketDataEntriesTest;
import com.google.code.atpfx.base.SpringTestBase;

import java.util.ArrayList;

public class TestMultipleConnections extends SpringTestBase {

    // test if we can connect on multiple accounts at the same time
	public void testMultipleConnectionsToMarket() {
		// have a list of connections
		ArrayList<IConnection> connections = new ArrayList<IConnection>();
		// open the connections
		AccountsList localAccountList = new AccountsList();
		assertTrue(
				"The test requires at least two accounts. Less then two found.",
				localAccountList.getAccountsList().size() > 1);
		for (AccountLoginDetails oneAccountLogin : localAccountList.getAccountsList()) {
			IConnection oneConnection = new FXCMConnection(
                    oneAccountLogin.getUserName(),
                    oneAccountLogin.getPassword(),
					oneAccountLogin.getAccountType());
			connections.add(oneConnection);
			oneConnection.open();
			assertTrue("The connection could not be established for one account.", oneConnection.isOpened());
			if (oneConnection.isOpened()) {
				System.out.println("Connection " + oneAccountLogin.getUserName() + " opened.");
			}
		}
		// wait a while
		secondsToWait(10);

		// close connections if they are opened
		for (IConnection conn1 : connections) {
            PrintEurUsdMarketDataEntriesTest tt = new PrintEurUsdMarketDataEntriesTest(conn1);

            // this is an fxcm conn due to test
            FXCMConnection conn = (FXCMConnection) conn1;

			if (conn.isOpened()) {
				conn.close();
				System.out.println("Connection " + conn.getUserName()+ " closed.");
				tt.printEntries(conn.getGenericMessagesReceived());
			}
		}
	}
}
