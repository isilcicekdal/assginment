package com.smartict.assginment.service;

import com.smartict.assginment.dto.StationDto;

import java.util.List;

public interface StationService {
    StationDto createStation(StationDto stationDto);

    List<StationDto> getStations();

    StationDto getStation(Long id);

    StationDto updateStation(Long id, StationDto stationDto);

    Boolean deleteStation(Long id);
}
