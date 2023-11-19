package com.example.sbabupg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JourneyPatternPointOnLine {
    @JsonProperty
    private int LineNumber;
    @JsonProperty
    private int DirectionCode;
    @JsonProperty
    private int JourneyPatternPointNumber;

    public JourneyPatternPointOnLine() {
    }

    public JourneyPatternPointOnLine(int lineNumber, int directionCode, int journeyPatternPointNumber) {
        LineNumber = lineNumber;
        DirectionCode = directionCode;
        JourneyPatternPointNumber = journeyPatternPointNumber;
    }

    public int getLineNumber() {
        return LineNumber;
    }

    public void setLineNumber(int lineNumber) {
        LineNumber = lineNumber;
    }

    public int getDirectionCode() {
        return DirectionCode;
    }

    public void setDirectionCode(int directionCode) {
        DirectionCode = directionCode;
    }

    public int getJourneyPatternPointNumber() {
        return JourneyPatternPointNumber;
    }

    public void setJourneyPatternPointNumber(int journeyPatternPointNumber) {
        JourneyPatternPointNumber = journeyPatternPointNumber;
    }
}
