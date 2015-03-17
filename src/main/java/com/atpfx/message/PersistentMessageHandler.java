package com.atpfx.message;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.atpfx.infrastructure.SignalProviderRepository;
import com.atpfx.infrastructure.TickRepository;
import com.atpfx.model.SignalProvider;
import com.atpfx.model.Tick;
import com.fxcm.fix.entity.MarketDataSnapshot;
import com.fxcm.messaging.ITransportable;

@Component
public class PersistentMessageHandler implements MessageHandler {

    public static final String FXCM_LABEL = "fxcm";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SignalProviderRepository signalProviderRepository;

    @Resource
    private TickRepository tickRepository;

    @Override
    public void handleMessage(ITransportable msg) {
        // TODO : inject signal provider based on context?
        SignalProvider signalProvider = signalProviderRepository.getByLabel(FXCM_LABEL);

        if (msg instanceof MarketDataSnapshot) {
            MarketDataSnapshot message = (MarketDataSnapshot) msg;
            Tick tick = new Tick(message, signalProvider);
            tickRepository.save(tick);
        } else {
            logger.error("Got something else than market snapshot: {}", msg);
        }
    }
}
