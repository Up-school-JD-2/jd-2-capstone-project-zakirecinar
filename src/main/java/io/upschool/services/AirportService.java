package io.upschool.services;

import io.upschool.dto.*;
import io.upschool.entity.Airport;
import io.upschool.entity.Companies;
import io.upschool.repository.AirportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor//zorunlu alanların constructor'ı oluşmalı(yani final)

public class AirportService {
    private final AirportsRepository airportsRepository;

    public AirportResponse getAirportById(AirportSearchDto airportSearchDto){
        Airport airport = airportsRepository.findById(airportSearchDto.getId()).get();
        return AirportResponse.builder()
                .id(airport.getId())
                .name(airport.getName())
                .code(airport.getCode())
                .build();
    }
    public List<Airport> findAirportByName(String name) {
        return airportsRepository.findAllByNameIs(name);
    }

    public Airport save(AirportRequest airportRequest){
        Airport airport=airportsRepository.save(Airport.builder()
                .code(airportRequest.getCode())
                .name(airportRequest.getName())
                .build()) ;
        return airport;
    }
    public List<Airport> getAllAirport(){
        return airportsRepository.findAll();
    }

    public Airport getAirportByID(Long airportID) {
        return airportsRepository.findById(airportID).orElseThrow();
    }
}
