package io.upschool.services;

import io.upschool.dto.AirportRequest;
import io.upschool.entity.Airport;
import io.upschool.repository.AirportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor//zorunlu alanların constructor'ı oluşmalı(yani final)

public class AirportService {
    private final AirportsRepository airportsRepository;
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
