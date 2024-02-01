package com.smartict.assginment.service;

import com.smartict.assginment.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    VehicleDto createVehicle(VehicleDto vehicleDto);

    List<VehicleDto> getVehicles();

    VehicleDto getVehicle(Long id);

    VehicleDto updateVehicle(Long id, VehicleDto vehicleDto);

    Boolean deleteVehicle(Long id);
}
