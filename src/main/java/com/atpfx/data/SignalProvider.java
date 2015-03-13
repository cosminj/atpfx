package com.atpfx.data;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SignalProvider {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String label;

    private String server;

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getServer() {
        return server;
    }
}
