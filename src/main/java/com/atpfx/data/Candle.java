package com.atpfx.data;

import static javax.persistence.GenerationType.AUTO;

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

}
