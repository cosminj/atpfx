package com.atpfx.model;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fxcm.fix.NotDefinedException;
import com.fxcm.fix.entity.MarketDataSnapshot;

@Entity
public class Tick {

    private static final Logger logger = LoggerFactory.getLogger(Tick.class);

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Pair pair;

    private double sell;

    private double buy;

    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    private SignalProvider signalProvider;

    /**
     * Needed by JPA
     */
    @SuppressWarnings("unused")
    protected Tick() {
    }

    public Tick(MarketDataSnapshot snap, SignalProvider signalProvider) {
        this.signalProvider = signalProvider;
        try {
            pair = Pair.valueOf(snap.getInstrument().getSymbol().toUpperCase());
        } catch (NotDefinedException ex) {
            logger.error("Couldn't compute pair from message {}", snap);
        }
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
