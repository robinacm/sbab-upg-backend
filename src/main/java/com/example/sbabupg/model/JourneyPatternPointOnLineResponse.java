package com.example.sbabupg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JourneyPatternPointOnLineResponse {
    @JsonProperty
    private ResponseData ResponseData;

    public JourneyPatternPointOnLineResponse.ResponseData getResponseData() {
        return ResponseData;
    }

    public void setResponseData(JourneyPatternPointOnLineResponse.ResponseData responseData) {
        ResponseData = responseData;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {
        @JsonProperty
        private List<JourneyPatternPointOnLine> Result;

        public ResponseData() {
        }

        public ResponseData(List<JourneyPatternPointOnLine> result) {
            Result = result;
        }

        public List<JourneyPatternPointOnLine> getResult() {
            return Result;
        }

        public void setResult(List<JourneyPatternPointOnLine> result) {
            Result = result;
        }
    }
}
