package com.atpfx.message;

import com.fxcm.messaging.ITransportable;

public interface MessageHandler {

    void handleMessage(ITransportable message);
}
