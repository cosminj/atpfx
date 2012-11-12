package com.google.code.atpfx.handler;

import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.fix.Instrument;
import com.fxcm.fix.UTCTimeOnly;
import com.fxcm.fix.pretrade.MarketDataSnapshot;
import com.fxcm.messaging.ITransportable;
import com.google.code.atpfx.data.Tick;

public class IDFHandler implements IGenericMessageListener {

    public void messageArrived(ITransportable msg) {
        if(msg instanceof MarketDataSnapshot) {
            try {
                MarketDataSnapshot quote = (MarketDataSnapshot) msg;
                Tick t = new Tick(quote);

                IDFMemoryBuffer memBuff = IDFMemoryBuffer.getIDFMemoryBuffer();
                memBuff.pushTick(t);

                System.out.println("tick: " + t);
            } catch(Exception ex) {
                System.out.println(ex);
            }
        }
        System.out.println("##: " + msg);
    }
}
