package com.atpfx.fxcm;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.atpfx.message.MessageHandler;
import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.messaging.ITransportable;

@Component
public class ForexMessageListener implements IGenericMessageListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private Set<MessageHandler> handlers;

    private Map<String, MessageHandler> handlerMap;

    MessageHandler<ITransportable> errorHandler = new MessageHandler<ITransportable>() {
        @Override
        public void handle(ITransportable aMessage) {
            logger.error("Cannot find handler for message type {}", aMessage.getType());
        }

        @Override
        public String fxcmType() {
            return null;
        }
    };

    @PostConstruct
    public void postConstruct() {
        handlerMap = handlers
            .stream()
            .collect(toMap(MessageHandler::fxcmType, identity()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void messageArrived(ITransportable aMessage) {
        handlerMap.getOrDefault(aMessage.getType().getCode(), errorHandler).handle(aMessage);
    }
}