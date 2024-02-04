package com.smartict.assginment.controller;


import com.smartict.assginment.dto.StationDto;
import com.smartict.assginment.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    private static final Logger log = LoggerFactory.getLogger(StationController.class);

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/create")
    public ResponseEntity<StationDto> createStation(@RequestBody StationDto stationDto) {

        StationDto station = stationService.createStation(stationDto);
        return new ResponseEntity<>(station, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StationDto>> getStations() {
        List<StationDto> getStations = stationService.getStations();
        return ResponseEntity.ok(getStations);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<StationDto> getStation(@PathVariable("id") Long id) {
        StationDto getStation = stationService.getStation(id);
        return ResponseEntity.ok(getStation);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StationDto> updateStation(@PathVariable("id") Long id,
                                                    @RequestBody StationDto stationDto) {
        StationDto station = stationService.updateStation(id, stationDto);
        return ResponseEntity.ok(station);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteStation(@PathVariable("id") Long id) {
        Boolean station = stationService.deleteStation(id);
        return ResponseEntity.ok(station);
    }
}
