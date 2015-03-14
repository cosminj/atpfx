package com.atpfx.fxcm;

import java.util.List;

import com.atpfx.forex.ForexConnection;
import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.messaging.ITransportable;

public class FxcmConnection implements ForexConnection {

    @Override
    public void addListener(IGenericMessageListener l) {

    }

    @Override
    public void open() {

    }

    @Override
    public boolean isOpened() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public List<ITransportable> getGenericMessagesReceived() {
        return null;
    }
}
