package com.atpfx.model;

import static javax.persistence.GenerationType.AUTO;

import java.time.Period;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Candle {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private SignalProvider signalProvider;

    private Pair pair;

    private Tick open;
    private Tick low;
    private Tick high;
    private Tick close;

    private Period interval;

    private Date openTime;

    /**
     * Needed by JPA
     */
    @SuppressWarnings("unused")
    protected Candle() {
    }

    public Candle(SignalProvider signalProvider, Pair pair, Tick open, Tick low, Tick high, Tick close, Period interval, Date openTime) {
        this.signalProvider = signalProvider;
        this.pair = pair;
        this.open = open;
        this.low = low;
        this.high = high;
        this.close = close;
        this.interval = interval;
        this.openTime = openTime;
    }

    public void checkNewHigh(Tick t) {
        if (t.getBuy() > high.getBuy()) {
            high = t;
        }
    }

    public void checkNewLow(Tick t) {
        if (t.getSell() < low.getSell()) {
            low = t;
        }
    }
}
