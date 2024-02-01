package com.smartict.assginment.service.impl;

import com.smartict.assginment.dto.RouteDto;
import com.smartict.assginment.dto.VehicleDto;
import com.smartict.assginment.exceptions.PlateNotNullException;
import com.smartict.assginment.exceptions.RecordNotFoundException;
import com.smartict.assginment.model.Route;
import com.smartict.assginment.model.Vehicle;
import com.smartict.assginment.repository.RouteRepository;
import com.smartict.assginment.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    private final ModelMapper mapper;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper mapper) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
    }


    @Override
    public RouteDto createRoute(RouteDto routeDto) {
        if (routeDto.getName().isBlank()) {
            throw new PlateNotNullException("Güzergah bilgisi dolu olmalı");
        } else {
            Route route = mapper.map(routeDto, Route.class);
            return mapper.map(routeRepository.save(route), RouteDto.class);
        }
    }

    @Override
    public List<RouteDto> getRoutes() {
        List<Route> routes = routeRepository.findAll();
        if (!routes.isEmpty()) {
            List<RouteDto> routeList = routes.stream()
                    .map(route -> mapper.map(route, RouteDto.class))
                    .collect(Collectors.toList());
            return routeList;
        }
        throw new RecordNotFoundException("Kayıtlı güzergah bulunmuyor.");
    }

    @Override
    public RouteDto getRoute(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Güzergah bulunamadı. " +
                        "Girilen değer: " + id));
        return mapper.map(route, RouteDto.class);
    }

    @Override
    public RouteDto updateRoute(Long id, RouteDto routeDto) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            route.get().setName(routeDto.getName());
            return mapper.map(routeRepository.save(route.get()), RouteDto.class);
        } else
            throw new RecordNotFoundException("Güzergah bulunamadı. Girilen değer: " + id);
    }

    @Override
    public Boolean deleteRoute(Long id) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            routeRepository.deleteById(id);
            return true;
        }
        throw new RecordNotFoundException("Güzergah bulunamadı. Girilen değer: " + id);
    }
}
