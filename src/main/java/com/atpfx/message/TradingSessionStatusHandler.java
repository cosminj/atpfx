package com.atpfx.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fxcm.fix.pretrade.TradingSessionStatus;

@Component("TradingSessionStatus")
public class TradingSessionStatusHandler implements MessageHandler<TradingSessionStatus> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(TradingSessionStatus tradingSessionStatus) {
        logger.info("handling trading status: {}", tradingSessionStatus);
    }

    @Override
    public String fxcmType() {
        return MessageType.TradingSessionStatus.fxcmCode();
    }
}