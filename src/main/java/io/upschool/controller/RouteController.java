package io.upschool.controller;


import io.upschool.dto.*;
import io.upschool.entity.Route;
import io.upschool.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<List<RouteResponse>> getRoute(){
        var routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }
    @PostMapping
    public Route createRoute(@RequestBody RouteRequest routeRequest){
        return routeService.save(routeRequest);
    }
    @PostMapping("/RouteId")
    public ResponseEntity<RouteResponse> getRouteById(@RequestBody RouteSearchDto routeSearchDto){
        return ResponseEntity.ok(routeService.getRouteById(routeSearchDto));
    }
}
