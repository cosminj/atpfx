package com.atpfx.model;

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

    private String serverUrl;

    /**
     * Needed by JPA
     */
    @SuppressWarnings("unused")
    protected SignalProvider() {
    }

    public SignalProvider(String label, String serverUrl) {
        this.label = label;
        this.serverUrl = serverUrl;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getServerUrl() {
        return serverUrl;
    }
}
