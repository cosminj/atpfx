package com.atpfx.message;

import com.fxcm.messaging.ITransportable;

public enum MessageType {

    MarketDataSnapshot("W", com.fxcm.fix.pretrade.MarketDataSnapshot.class),
    TradingSessionStatus("h", com.fxcm.fix.pretrade.TradingSessionStatus.class);

    private final String code;
    private final Class<? extends ITransportable> type;

    MessageType(String code, Class<? extends ITransportable> type) {
        this.code = code;
        this.type = type;
    }

    public String fxcmCode() {
        return code;
    }

    public Class<? extends ITransportable> fxcmType() {
        return type;
    }
}
