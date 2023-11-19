package com.example.sbabupg.controller;

import com.example.sbabupg.DTO.StopsOnLineDTO;
import com.example.sbabupg.service.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusLineController {

    @Autowired
    BusLineService busLineService;

    @GetMapping("/top-bus-lines")
    public ResponseEntity<List<StopsOnLineDTO>> getTopBusLines() {

        // Vi skulle kunna plocka ut StatusCode som trafiklab returnerar i API-kallen och agera därefter
        final List<StopsOnLineDTO> stopsOnLineDTOList = busLineService.getTopBusLines();

        return ResponseEntity.ok().body(stopsOnLineDTOList);
    }
}
