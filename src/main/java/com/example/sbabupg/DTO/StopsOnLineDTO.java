package com.example.sbabupg.DTO;

import java.util.List;

public class StopsOnLineDTO {
    private int line;
    private int numberOfStops;
    private List<String> stopPoints;

    public StopsOnLineDTO(int line, int numberOfStops, List<String> stopPoints) {
        this.line = line;
        this.numberOfStops = numberOfStops;
        this.stopPoints = stopPoints;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public List<String> getStopPoints() {
        return stopPoints;
    }

    public void setStopPoints(List<String> stopPoints) {
        this.stopPoints = stopPoints;
    }
}
