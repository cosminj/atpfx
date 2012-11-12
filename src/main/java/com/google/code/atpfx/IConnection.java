package com.google.code.atpfx;

import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.messaging.ITransportable;

import java.util.List;

/**
 * defines typology for all possibble connections s.a.
 * - FXCM
 * - mock
 * - others next (s.a. bkts !?)
 */
public interface IConnection {
    void addListener(IGenericMessageListener l);

    void open();

    boolean isOpened();

    void close();

    List<ITransportable> getGenericMessagesReceived();
}