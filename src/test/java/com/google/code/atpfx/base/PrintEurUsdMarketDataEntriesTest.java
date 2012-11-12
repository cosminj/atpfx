/*
 * Copyright (c) 2011. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.google.code.atpfx.base;

import com.fxcm.fix.NotDefinedException;
import com.fxcm.fix.UTCTimestamp;
import com.fxcm.fix.pretrade.MarketDataSnapshot;
import com.fxcm.messaging.ITransportable;
import com.google.code.atpfx.FXCMConnection;
import com.google.code.atpfx.IConnection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrintEurUsdMarketDataEntriesTest {
     private IConnection connection;

    public ArrayList<ITransportable> getEurUsdMessagesOnly() {
        ArrayList<ITransportable> genericMessagesReceivedOnlyEURUSD = new ArrayList<ITransportable>();
        for (ITransportable message : connection.getGenericMessagesReceived()) {
            if (message instanceof MarketDataSnapshot) {
                MarketDataSnapshot incomingQuote = (MarketDataSnapshot) message;
                try {
                    if (incomingQuote.getInstrument().getSymbol().equalsIgnoreCase("EUR/USD")) {
                        genericMessagesReceivedOnlyEURUSD.add(message);
                    }
                } catch (NotDefinedException e) {
                    e.printStackTrace();
                }
            }
        }
        return genericMessagesReceivedOnlyEURUSD;
    }

    public PrintEurUsdMarketDataEntriesTest(IConnection connection) {
        this.connection = connection;
    }

    // public method for testing purposes only
    public boolean gotEurUsdData() {
        return getEurUsdMessagesOnly().size() > 0;
    }

    @SuppressWarnings("unchecked")
    public void printEntries(List<ITransportable> messages) {
        long tempLong = 0;
        long counter = 1;

        for (ITransportable message : messages) {
            MarketDataSnapshot aMds = (MarketDataSnapshot) message;
            System.out.println("client: streaming = entry " + counter++);
            System.out.println("quote open GMT date/time: " + aMds.getOpenTimestamp() +
                    " , close GMT date/time: " + aMds.getCloseTimestamp());

            tempLong = aMds.getOpenTimestamp().getTime();
            System.out.println("Recreating the date: " + new UTCTimestamp(new Date(tempLong)));


            System.out.print("Sell: " + aMds.getBidClose());
            System.out.print("Buy : " + aMds.getAskClose());
            System.out.print("Max: " + aMds.getHigh());
            System.out.print("Min: " + aMds.getLow());

            // TODO: ????? System.out.println(marketDataEntry.getMDEntryPx());
        }
    }
}