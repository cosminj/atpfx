package com.atpfx.message;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.atpfx.infrastructure.SignalProviderRepository;
import com.atpfx.infrastructure.TickRepository;
import com.atpfx.model.SignalProvider;
import com.atpfx.model.Tick;
import com.fxcm.fix.pretrade.MarketDataSnapshot;

@Component
public class MarketDataSnapshotHandler implements MessageHandler<MarketDataSnapshot> {

    public static final String FXCM_LABEL = "fxcm";

    @Resource
    private SignalProviderRepository signalProviderRepository;
    @Resource
    private TickRepository tickRepository;

    private SignalProvider fxcmProvider;

    @PostConstruct
    public void postConstruct() {
        // TODO : inject signal provider based on context?
        fxcmProvider = signalProviderRepository.getByLabel(FXCM_LABEL);
    }

    @Transactional
    @Override
    public void handle(MarketDataSnapshot marketDataSnapshot) {
        Tick tick = new Tick(marketDataSnapshot, fxcmProvider);
        tickRepository.save(tick);
    }

    @Override
    public String fxcmType() {
        return MessageType.MarketDataSnapshot.fxcmCode();
    }
}