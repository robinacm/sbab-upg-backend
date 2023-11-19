package com.example.sbabupg.service;

import com.example.sbabupg.DTO.StopsOnLineDTO;
import com.example.sbabupg.model.JourneyPatternPointOnLine;
import com.example.sbabupg.model.JourneyPatternPointOnLineResponse;
import com.example.sbabupg.model.Stop;
import com.example.sbabupg.model.StopResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusLineService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    // OBS! Krävs en api-nyckel i URL:en - skapa en nyckel på trafiklab sidan och lägg till t.ex. &key=abcnyckel123
    final String baseApiURL = "https://api.sl.se/api2/LineData.json?DefaultTransportModeCode=BUS";

    public List<StopsOnLineDTO> getTopBusLines() {
        List<JourneyPatternPointOnLine> busLines = getLines();
        List<Stop> busStops = getStops();

        Map<Integer, List<JourneyPatternPointOnLine>> lineJourneyMap = mapBuslinesToJourney(busLines);
        return sortByTopTen(lineJourneyMap, busStops);
    }

    private Map<Integer, List<JourneyPatternPointOnLine>> mapBuslinesToJourney(final List<JourneyPatternPointOnLine> busLines) {
        return busLines.stream()
                .filter(entry -> entry.getDirectionCode() == 1)
                .collect(Collectors.groupingBy(
                        JourneyPatternPointOnLine::getLineNumber,
                        Collectors.mapping(entry -> entry, Collectors.toList())
                ));
    }

    private List<StopsOnLineDTO> sortByTopTen(final Map<Integer, List<JourneyPatternPointOnLine>>  lineToJourneyPatternMap, List<Stop> busStops) {
        return lineToJourneyPatternMap.entrySet()
                .stream()
                .sorted(Comparator.<Map.Entry<Integer, List<JourneyPatternPointOnLine>>>comparingInt(e -> e.getValue().size()).reversed())
                .limit(10)
                .map(entry -> {
                    int line = entry.getKey();
                    List<String> stopPoints = entry.getValue()
                            .stream()
                            .map(JourneyPatternPointOnLine::getJourneyPatternPointNumber)
                            .map(pointNumber -> findStopByJourneyPatternPointNumber(busStops, pointNumber))
                            .map(Stop::getStopPointName)
                            .collect(Collectors.toList());

                    return new StopsOnLineDTO(line, stopPoints.size(), stopPoints);
                })
                .collect(Collectors.toList());
    }

    private List<JourneyPatternPointOnLine> getLines() {
        final String apiURL = baseApiURL + "&model=jour";
        String response = externalAPIResponse(apiURL);
        JourneyPatternPointOnLineResponse journeyPatternPointOnLineResponse = null;
        try {
            journeyPatternPointOnLineResponse = objectMapper.readValue(response, JourneyPatternPointOnLineResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return journeyPatternPointOnLineResponse != null ? journeyPatternPointOnLineResponse.getResponseData().getResult() : new ArrayList<>();
    }

    private List<Stop> getStops() {
        final String apiURL = baseApiURL + "&model=stop";
        String response = externalAPIResponse(apiURL);
        StopResponse stopResponse = null;
        try {
            stopResponse = objectMapper.readValue(response, StopResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return stopResponse != null ? stopResponse.getResponseData().getResult() : new ArrayList<>();
    }

    private Stop findStopByJourneyPatternPointNumber(final List<Stop> stops, final int journeyPatternPointNumber) {
        return stops.stream()
                .filter(stop -> stop.getStopPointNumber() == journeyPatternPointNumber)
                .findFirst()
                .orElse(new Stop());
    }

    private String externalAPIResponse(final String apiURL) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiURL, String.class);
    }
}
