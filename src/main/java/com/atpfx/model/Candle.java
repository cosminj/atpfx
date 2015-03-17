package com.atpfx.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

import java.time.Period;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Candle {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Enumerated(STRING)
    private Pair pair;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tick open;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tick low;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tick high;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tick close;

    private Period interval;

    private Date openTime;

    /**
     * Needed by JPA
     */
    @SuppressWarnings("unused")
    protected Candle() {
    }

    public Candle(Pair pair, Tick open, Tick low, Tick high, Tick close, Period interval, Date openTime) {
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
