package io.upschool.controller;


import io.upschool.dto.*;
import io.upschool.entity.Flight;
import io.upschool.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightResponse>> getFlight(){
        var flights=flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }
    @PostMapping
    public Flight createFlight(@RequestBody FlightRequest flightRequest){
        return flightService.save(flightRequest);
    }
    @PostMapping("/flightID")
    public ResponseEntity<FlightResponse> getFlightsById(@RequestBody FlightSearchDto flightSearchDto){
        return ResponseEntity.ok(flightService.getFlightById(flightSearchDto));
    }
}
