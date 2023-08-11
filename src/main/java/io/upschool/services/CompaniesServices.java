package io.upschool.services;

import io.upschool.dto.CompanyRequest;
import io.upschool.dto.CompanyResponse;
import io.upschool.dto.CompanySearchDto;
import io.upschool.entity.Airport;
import io.upschool.entity.Companies;
import io.upschool.repository.CompaniesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompaniesServices {
    private final CompaniesRepository companiesRepository;
    private final AirportService airportService;
    public CompanyResponse getCompanyById(CompanySearchDto companySearchDto){
        Companies companies = companiesRepository.findById(companySearchDto.getId()).get();
        return CompanyResponse.builder()
               .id(companies.getId())
                .name(companies.getName())
                .country(companies.getCountry())
                .airportID(companies.getAirport().getId()).build();
    }


    //select * from companies
    public List<CompanyResponse> getAllCompanies() {
        var companyResponse = companiesRepository.findAll().stream().map(companies -> CompanyResponse.builder()
                .id(companies.getId())
                .airportID(companies.getAirport().getId())
                .country(companies.getCountry())
                .name(companies.getName())
                .build()).toList();
        return companyResponse;
    }

    public Companies save(CompanyRequest companyRequest) {
        Companies companies = companiesRepository.save(Companies.builder()
                .name(companyRequest.getName())
                .country(companyRequest.getCountry())
                .airport(companyRequest.getAirport())
                .build());
        return companies;
    }

    public List<CompanyResponse> getCompaniesByAirportID(Long airportID ) {
              Airport airport=airportService.getAirportByID(airportID);

        List<Companies> list = companiesRepository.findAll().stream()
                .filter(companies -> companies.getAirport().getId().equals(airport.getId()))
                        .toList();

        List<CompanyResponse> companyResponse = list.stream().map(companies -> CompanyResponse.builder()
                .id(companies.getId())
                .airportID(companies.getAirport().getId())
                .country(companies.getCountry())
                .name(companies.getName())
                .build()).toList();
        return companyResponse;
    }
}
