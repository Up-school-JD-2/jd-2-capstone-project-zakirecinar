package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Airport;
import io.upschool.services.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor

public class AirportController {
    private final AirportService airportService;
    @GetMapping
    public ResponseEntity<List<Airport>> getAirports(){
        var airports=airportService.getAllAirport();
        return ResponseEntity.ok(airports);
        //badrequest olursa bir veri dönmeyiz içine direkt hata verir
    }
    @PostMapping
    public Airport createAirport(@RequestBody AirportRequest airportRequest){
    return airportService.save(airportRequest);
    }
    @PostMapping("/companyID")
    public ResponseEntity<AirportResponse> getAirportsByCompanyID(@RequestBody AirportSearchDto airportSearchDto){
        return ResponseEntity.ok(airportService.getAirportById(airportSearchDto));
    }

}
