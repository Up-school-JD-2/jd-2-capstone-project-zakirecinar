package io.upschool.services;

import io.upschool.dto.RouteRequest;
import io.upschool.dto.RouteResponse;
import io.upschool.entity.Route;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    public List<RouteResponse> getAllRoutes(){
        var routeResponse = routeRepository.findAll().stream().map(route -> RouteResponse.builder()
                .id(route.getId())
                .sourceAirportCode(route.getSourceAirportCode())
                .destinationAirportCode(route.getDestinationAirportCode())
                .build())
                .toList();
        return routeResponse;
    }
    public Route save(RouteRequest routeRequest){
        Route route = routeRepository.save(Route.builder()
                .sourceAirportCode(routeRequest.getSourceAirportCode())
                .destinationAirportCode(routeRequest.getDestinationAirportCode())
                .build());
        return route;
    }
}
