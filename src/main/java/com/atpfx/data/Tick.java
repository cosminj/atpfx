package com.atpfx.data;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fxcm.fix.NotDefinedException;
import com.fxcm.fix.entity.MarketDataSnapshot;

@Entity
public class Tick {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Pair pair;

    private double sell;

    private double buy;

    private Date time;

    private SignalProvider signalProvider;

    /**
     * Needed by JPA
     */
    @SuppressWarnings("unused")
    protected Tick() {}

    public Tick(Pair pair, double sell, double buy, Date time, SignalProvider signalProvider) {
        this.pair = pair;
        this.sell = sell;
        this.buy = buy;
        this.time = time;
        this.signalProvider = signalProvider;
    }

    public Tick(MarketDataSnapshot snap, SignalProvider signalProvider) throws NotDefinedException {
        this.signalProvider = signalProvider;
        pair = Pair.valueOf(snap.getInstrument().getSymbol().toUpperCase());
        sell = snap.getBidClose();
        buy = snap.getAskClose();
        time = new Date(snap.getOpenTimestamp().getTime());
    }

    public Long getId() {
        return id;
    }

    public Pair getPair() {
        return pair;
    }

    public double getSell() {
        return sell;
    }

    public double getBuy() {
        return buy;
    }

    public Date getTime() {
        return time;
    }

    public SignalProvider getSignalProvider() {
        return signalProvider;
    }
}
