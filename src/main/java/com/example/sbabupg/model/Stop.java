package com.example.sbabupg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stop {
    @JsonProperty
    private int StopPointNumber;
    @JsonProperty
    private String StopPointName;

    public Stop() {
    }

    public Stop(final int stopPointNumber,final String stopPointName) {
        this.StopPointNumber = stopPointNumber;
        this.StopPointName = stopPointName;
    }

    public int getStopPointNumber() {
        return StopPointNumber;
    }

    public void setStopPointNumber(int stopPointNumber) {
        StopPointNumber = stopPointNumber;
    }

    public String getStopPointName() {
        return StopPointName;
    }

    public void setStopPointName(String stopPointName) {
        StopPointName = stopPointName;
    }
}
