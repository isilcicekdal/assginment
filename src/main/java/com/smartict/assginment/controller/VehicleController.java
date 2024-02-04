package com.smartict.assginment.controller;

import com.smartict.assginment.dto.VehicleDto;
import com.smartict.assginment.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/create")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
        VehicleDto vehicle = vehicleService.createVehicle(vehicleDto);
        return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<VehicleDto>> getVehicles() {
        List<VehicleDto> getVehicles = vehicleService.getVehicles();
        return ResponseEntity.ok(getVehicles);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<VehicleDto> getVehicle(@PathVariable("id") Long id) {
        VehicleDto getVehicle = vehicleService.getVehicle(id);
        return ResponseEntity.ok(getVehicle);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable("id") Long id,
                                                      @RequestBody VehicleDto vehicleDto) {
        VehicleDto vehicle = vehicleService.updateVehicle(id, vehicleDto);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteVehicle(@PathVariable("id") Long id) {
        Boolean vehicle = vehicleService.deleteVehicle(id);
        return ResponseEntity.ok(vehicle);
    }


}
