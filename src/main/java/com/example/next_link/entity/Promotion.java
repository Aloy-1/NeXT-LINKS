package com.example.next_link.entity;

import lombok.Getter;

@Getter
public enum Promotion {
    MSP1("MSP1"), MSP2("MSP2"), POLYTECH_OTHER("Polytech, autre"), NOT_POLYTECH("Pas à Polytech");
    private final String label;

    Promotion(String label) {
        this.label = label;
    }
}
