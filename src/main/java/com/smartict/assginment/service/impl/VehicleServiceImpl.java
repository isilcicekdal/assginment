package com.smartict.assginment.service.impl;

import com.smartict.assginment.exceptions.PlateNotNullException;
import com.smartict.assginment.exceptions.RecordNotFoundException;
import com.smartict.assginment.model.Vehicle;
import com.smartict.assginment.repository.VehicleRepository;
import com.smartict.assginment.dto.VehicleDto;
import com.smartict.assginment.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final StationServiceImpl stationService;

    private final ModelMapper mapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, StationServiceImpl stationService, ModelMapper mapper) {
        this.vehicleRepository = vehicleRepository;
        this.stationService = stationService;
        this.mapper = mapper;
    }

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        if (vehicleDto.getPlate() == null) {
            throw new PlateNotNullException("Plaka bilgisi dolu olmalı");
        } else {
            Vehicle vehicle = mapper.map(vehicleDto, Vehicle.class);
            return mapper.map(vehicleRepository.save(vehicle), VehicleDto.class);
        }
    }

    @Override
    public List<VehicleDto> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (!vehicles.isEmpty()) {
            List<VehicleDto> vehicleList = vehicles.stream()
                    .map(vehicle -> mapper.map(vehicle, VehicleDto.class))
                    .collect(Collectors.toList());
            return vehicleList;
        }
        throw new RecordNotFoundException("Kayıtlı araç bulunmuyor.");
    }

    @Override
    public VehicleDto getVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Araç bulunamadı. " +
                        "Girilen değer: " + id));
        return mapper.map(vehicle, VehicleDto.class);
    }

    @Override
    public VehicleDto updateVehicle(Long id, VehicleDto vehicleDto) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            vehicle.get().setPlate(vehicleDto.getPlate());
         //   vehicle.get().setRoute(vehicleDto.getRoute());
            return mapper.map(vehicleRepository.save(vehicle.get()), VehicleDto.class);
        } else
            throw new RecordNotFoundException("Kayıt bulunamadı. Girilen değer: " + id);
    }

    @Override
    public Boolean deleteVehicle(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(id);
            return true;
        }
        throw new RecordNotFoundException("Kayıt bulunamadı. Girilen değer: " + id);
    }
}
