package com.google.code.atpfx.data;

import com.fxcm.fix.NotDefinedException;
import com.fxcm.fix.pretrade.MarketDataSnapshot;
import com.fxcm.messaging.ITransportable;

public class Tick {
    private long signalProviderId;
    private long pairId;
    // pair assumes standard FIX names
    // the pairs are EUR/USD=1, GBP/USD=2, ..., and these constants are stored in DB
    private double sell;//a.k.a ask
    private double buy;//a.k.a bid
    private long timestamp;
    private long id;

    private Pair pair;

    public Tick() {

    }

    public Tick(ITransportable message) {
        if (message instanceof MarketDataSnapshot) {
            MarketDataSnapshot aMds = (MarketDataSnapshot) message;
            try {
                this.setSignalProviderId(signalProviderId);
                Pair pair = Pairs.getPair(aMds.getInstrument().getSymbol().toUpperCase());
                this.setPairId(pair.getPairId());
                this.setPair(pair);
                this.setSell(aMds.getBidClose());
                this.setBuy(aMds.getAskClose());
                this.setTimestamp(aMds.getOpenTimestamp().getTime());

            } catch (NotDefinedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getSignalProviderId() {
        return signalProviderId;
    }

    public void setSignalProviderId(long signalProviderId) {
        this.signalProviderId = signalProviderId;
    }

    public long getPairId() {
        return pairId;
    }

    public void setPairId(Long pairId) {
        this.pairId = pairId;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long l) {
        this.timestamp = l;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public String toString() {
        return signalProviderId + "/Pair=" + this.getPair() + " " + this.sell + "/" + this.buy + " " + (new java.util.Date(timestamp).toString()) + " ID=" + this.getId();
    }
}
