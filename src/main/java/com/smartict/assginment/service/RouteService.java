package com.smartict.assginment.service;

import com.smartict.assginment.dto.RouteDto;
import com.smartict.assginment.dto.VehicleDto;

import java.util.List;

public interface RouteService {
    RouteDto createRoute(RouteDto routeDto);

    List<RouteDto> getRoutes();

    RouteDto getRoute(Long id);

    RouteDto updateRoute(Long id, RouteDto routeDto);

    Boolean deleteRoute(Long id);
}
