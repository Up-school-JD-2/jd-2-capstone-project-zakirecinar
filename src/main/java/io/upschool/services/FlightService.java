package io.upschool.services;


import io.upschool.dto.FlightRequest;
import io.upschool.dto.FlightResponse;
import io.upschool.entity.Flight;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public List<FlightResponse> getAllFlights() {
        var flightResponse = flightRepository.findAll().stream().map(flight -> FlightResponse.builder()
                .id(flight.getId())
                .routeId(flight.getRouteId())
                .companyName(flight.getCompanyName())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .build())
                .toList();
        return flightResponse;
    }

    public Flight save(FlightRequest flightRequest) {
        Flight flight = flightRepository.save(Flight.builder()
                        .routeId(flightRequest.getRouteId())
                        .companyName(flightRequest.getCompanyName())
                        .departureTime(flightRequest.getDepartureTime())
                        .arrivalTime(flightRequest.getArrivalTime())
                .build());
        return flight;
    }

}
