package com.smartict.assginment.controller;

import com.smartict.assginment.dto.RouteDto;
import com.smartict.assginment.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    private static final Logger log = LoggerFactory.getLogger(RouteController.class);

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    @PostMapping("/create")
    public ResponseEntity<RouteDto> createRoute(@RequestBody RouteDto routeDto) {
        log.info("/route/createRoute metodu çalıştı");
        RouteDto route = routeService.createRoute(routeDto);
        return new ResponseEntity<>(route, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RouteDto>> getRoutes() {
        log.info("/getAll/getRoutes metodu çalıştı");
        List<RouteDto> getRoutes = routeService.getRoutes();
        return ResponseEntity.ok(getRoutes);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<RouteDto> getRoute(@PathVariable("id") Long id) {
        log.info("/getById/getRoute metodu çalıştı");
        RouteDto getRoute = routeService.getRoute(id);
        return ResponseEntity.ok(getRoute);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RouteDto> updateRoute(@PathVariable("id") Long id,
                                                    @RequestBody RouteDto routeDto) {
        log.info("/update/updateRoute metodu çalıştı");
        RouteDto route = routeService.updateRoute(id, routeDto);
        return ResponseEntity.ok(route);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRoute(@PathVariable("id") Long id) {
        log.info("/delete/deleteRoute metodu çalıştı");
        Boolean route = routeService.deleteRoute(id);
        return ResponseEntity.ok(route);
    }
}
