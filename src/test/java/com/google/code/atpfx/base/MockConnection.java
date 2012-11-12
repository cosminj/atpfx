package com.google.code.atpfx.base;

import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.fix.pretrade.MarketDataSnapshot;
import com.fxcm.messaging.ITransportable;
import com.google.code.atpfx.IConnection;

import java.util.LinkedList;
import java.util.List;

/**
 * @see com.google.code.atpfx.IConnection
 */
public class MockConnection implements IConnection {

    private static final MockMarketGenerator mmg = new MockMarketGenerator();

    private List<ITransportable> allMessages = new LinkedList<ITransportable>();

    // emulate the FxcmGateway - IMessageListeners
    private List<IGenericMessageListener> listeners = new LinkedList<IGenericMessageListener>();

    @Override
    public void addListener(IGenericMessageListener l) {
        listeners.add(l);
    }

    private Thread x;

    @Override
    public void open() {
        // mock open
        System.out.println("Mock connection opened. Listening random generator.");
        x = new Thread() {
            int x = 0;

            @Override
            public void run() {
                try {
                    while(x == 0) {


                        MarketDataSnapshot mds = mmg.getMockMarketSnapshot();

                        allMessages.add(mds);

                        for (IGenericMessageListener list : listeners) {
                            list.messageArrived(mds);
                        }
                                                
                        // sleep 1 sec
                        Thread.sleep(1000);
                    }
                } catch(InterruptedException ex) {
                    System.err.println(ex);
                }
            }
        };

        x.start();
    }

    @Override
    public boolean isOpened() {
        return true;
    }

    @Override
    public void close() {
        // try to stop x
        x.interrupt();

        allMessages = null;
        listeners = null;
    }

    @Override
    public List<ITransportable> getGenericMessagesReceived() {
        return null;
    }
}