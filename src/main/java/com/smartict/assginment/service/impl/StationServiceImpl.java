package com.smartict.assginment.service.impl;

import com.smartict.assginment.dto.StationDto;
import com.smartict.assginment.dto.VehicleDto;
import com.smartict.assginment.exceptions.RecordNotFoundException;
import com.smartict.assginment.exceptions.StationNotNullException;
import com.smartict.assginment.model.Station;
import com.smartict.assginment.model.Vehicle;
import com.smartict.assginment.repository.StationRepository;
import com.smartict.assginment.service.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    private final ModelMapper mapper;

    public StationServiceImpl(StationRepository stationRepository, ModelMapper mapper) {
        this.stationRepository = stationRepository;
        this.mapper = mapper;
    }

    @Override
    public StationDto createStation(StationDto stationDto) {
        if (stationDto.getName().isEmpty()) {
            throw new StationNotNullException("İstasyon bilgisi dolu olmalı");
        } else {
            Station sttaion = mapper.map(stationDto, Station.class);
            return mapper.map(stationRepository.save(sttaion), StationDto.class);
        }
    }

    @Override
    public List<StationDto> getStations() {
        List<Station> stations = stationRepository.findAll();
        if (!stations.isEmpty()) {
            List<StationDto> stationList = stations.stream()
                    .map(station -> mapper.map(station, StationDto.class))
                    .collect(Collectors.toList());
            return stationList;
        }
        throw new RecordNotFoundException("Kayıtlı istasyon bulunmuyor.");
    }

    @Override
    public StationDto getStation(Long id) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Kayıtlı istasyon bulunmuyor. " +
                        "Girilen değer: " + id));
        return mapper.map(station, StationDto.class);
    }

    @Override
    public StationDto updateStation(Long id, StationDto stationDto) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()) {
            station.get().setName(stationDto.getName());
            return mapper.map(stationRepository.save(station.get()), StationDto.class);
        } else
            throw new RecordNotFoundException("Kayıt bulunamadı. Girilen değer: " + id);
    }

    @Override
    public Boolean deleteStation(Long id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()) {
            stationRepository.deleteById(id);
            return true;
        }
        throw new RecordNotFoundException("Kayıt bulunamadı. Girilen değer: " + id);
    }
}
