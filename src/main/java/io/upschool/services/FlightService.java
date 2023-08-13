package io.upschool.services;


import io.upschool.dto.*;
import io.upschool.entity.Companies;
import io.upschool.entity.Flight;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightResponse getFlightById(TicketSearchDto ticketSearchDto){
        Flight flight = flightRepository.findById(ticketSearchDto.getId()).get();
        return FlightResponse.builder()
                .id(flight.getId())
                .companyId(flight.getCompanyId())
                .routeId(flight.getId())
                .arrivalTime(flight.getArrivalTime())
                .departureTime(flight.getDepartureTime())
                .companyName(flight.getCompanyName())
                .build();
    }

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
