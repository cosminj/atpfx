package com.atpfx.forex;

import java.util.List;

import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.messaging.ITransportable;

public interface ForexConnection {

    void addListener(IGenericMessageListener l);

    void open();

    boolean isOpened();

    void close();

    List<ITransportable> getGenericMessagesReceived();

}