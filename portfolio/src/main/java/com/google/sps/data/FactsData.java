package com.google.sps.data;

/**
 * stores the facts data type
 */
public class FactsData {
    private final String fact;
    public FactsData(String fact){
        this.fact = fact;
    }
    public String getFact() {
        return fact;
    }
}
