package io.upschool.services;
import io.upschool.dto.*;
import io.upschool.entity.Airport;
import io.upschool.entity.Ticket;
import io.upschool.exception.AirportAlreadySavedException;
import io.upschool.exception.AirportNotFoundException;
import io.upschool.exception.GlobalExceptionHandler;
import io.upschool.exception.TicketNotFoundException;
import io.upschool.repository.AirportsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor//zorunlu alanların constructor'ı oluşmalı(yani final)
@Transactional()
public class AirportService {
    private final AirportsRepository airportsRepository;

    public AirportResponse getAirportById(AirportSearchDto airportSearchDto){

        Optional<Airport> optionalAirport = airportsRepository.findById(airportSearchDto.getId());

        if (optionalAirport.isPresent()) {
            Airport airport = optionalAirport.get();
            return AirportResponse.builder()
                    .id(airport.getId())
                    .name(airport.getName())
                    .code(airport.getCode())
                    .build();
        }else {
            throw new AirportNotFoundException("Airport not found");
        }

    }
    public List<Airport> findAirportByName(String name) {
        return airportsRepository.findAllByNameIs(name);
    }

    public Airport save(AirportRequest airportRequest){
        if(airportsRepository.existsByName(airportRequest.getName())){
            throw new AirportAlreadySavedException("airport exists");
        }
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
