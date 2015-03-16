package com.atpfx.fxcm;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.atpfx.message.MessageHandler;
import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.messaging.ITransportable;

@Component
public class ForexMessageListener implements IGenericMessageListener {

    @Resource
    private Set<MessageHandler> messageHandlers;

    @Override
    public void messageArrived(ITransportable aMessage) {
        messageHandlers.forEach(h -> h.handleMessage(aMessage));
    }
}
