/*
 * Copyright (c) 2011. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.google.code.atpfx.base;

import com.fxcm.fix.Instrument;
import com.fxcm.fix.UTCDate;
import com.fxcm.fix.UTCTimeOnly;
import com.fxcm.fix.UTCTimestamp;
import com.fxcm.fix.pretrade.MarketDataSnapshot;
import com.fxcm.messaging.IMessage;
import com.fxcm.messaging.IMessageFactory;
import com.google.code.atpfx.data.Pair;
import com.google.code.atpfx.data.Pairs;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class MockMarketGenerator {

    // class level random seed
    public static final Random rand = new Random();

    private String nextRandomPair() {
        List<Pair> all = Pairs.getAllPairs();
        int pairIdx = rand.nextInt(all.size());
        return all.get(pairIdx).getPairName();
    }

    public MarketDataSnapshot getMockMarketSnapshot() {
        // anonymous class - mocking a snapshot - random
        return new MockMarketDataSnapshotAdapter() {
            private double bidClose = rand.nextDouble();
            private double askClose = rand.nextDouble();

            @Override
            public Instrument getInstrument() {
                return new Instrument(nextRandomPair());
            }

            @Override
            public double getBidClose() {
                return bidClose;
            }

            @Override
            public double getAskClose() {
                return askClose;
            }

            @Override
            public UTCTimestamp getOpenTimestamp() { 
                return new UTCTimestamp(new Date(new Date().getTime() - 1000));
            }

            @Override
            public double getHigh() {
                return askClose + 0.001; 
            }

            @Override
            public double getLow() {
                return bidClose - 0.001;
            }
        };
    }
}