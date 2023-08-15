package io.upschool.services;


import io.upschool.dto.*;
import io.upschool.entity.Companies;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.exception.FlightAlreadySavedException;
import io.upschool.exception.TicketAlreadySavedException;
import io.upschool.exception.TicketNotFoundException;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightResponse getFlightById(FlightSearchDto flightSearchDto) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightSearchDto.getId());
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            return FlightResponse.builder()
                    .id(flight.getId())
                    .companyId(flight.getCompanyId())
                    .routeId(flight.getId())
                    .arrivalTime(flight.getArrivalTime())
                    .departureTime(flight.getDepartureTime())
                    .companyName(flight.getCompanyName())
                    .build();
        }else {
            throw new TicketNotFoundException("Flight not found");
        }

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
        if (flightRepository.existsById(flightRequest.getRouteId())) {
            throw new FlightAlreadySavedException("There is a flight with this id.");
        }
        Flight flight = flightRepository.save(Flight.builder()
                .routeId(flightRequest.getRouteId())
                .companyName(flightRequest.getCompanyName())
                .departureTime(flightRequest.getDepartureTime())
                .arrivalTime(flightRequest.getArrivalTime())
                .build());
        return flight;
    }

}
