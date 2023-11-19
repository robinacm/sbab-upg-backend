package com.example.sbabupg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopResponse {
    @JsonProperty
    private ResponseData ResponseData;

    public StopResponse.ResponseData getResponseData() {
        return ResponseData;
    }

    public void setResponseData(StopResponse.ResponseData responseData) {
        ResponseData = responseData;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {
        @JsonProperty
        private List<Stop> Result;

        public ResponseData() {
        }

        public ResponseData(List<Stop> result) {
            Result = result;
        }

        public List<Stop> getResult() {
            return Result;
        }

        public void setResult(List<Stop> result) {
            Result = result;
        }
    }
}
