package io.upschool.services;

import io.upschool.dto.CompanyRequest;
import io.upschool.dto.CompanyResponse;
import io.upschool.dto.CompanySearchDto;
import io.upschool.entity.Airport;
import io.upschool.entity.Companies;
import io.upschool.exception.CompanyAlreadySavedException;
import io.upschool.exception.CompanyNotFoundException;
import io.upschool.exception.TicketNotFoundException;
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

    public CompanyResponse getCompanyById(CompanySearchDto companySearchDto) {
        Optional<Companies> optionalCompanies = companiesRepository.findById(companySearchDto.getId());
        if (optionalCompanies.isPresent()) {
            Companies companies = optionalCompanies.get();
            return CompanyResponse.builder()
                    .id(companies.getId())
                    .name(companies.getName())
                    .country(companies.getCountry())
                    .airportID(companies.getAirport().getId()).build();
        } else {
            throw new CompanyNotFoundException("Airline number "+companySearchDto.getId()+ " not found");
        }
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
        if (companiesRepository.existsById(companyRequest.getAirport().getId())) {
            throw new CompanyAlreadySavedException("The airline is exists");
        }
        Companies companies = companiesRepository.save(Companies.builder()
                .name(companyRequest.getName())
                .country(companyRequest.getCountry())
                .airport(companyRequest.getAirport())
                .build());
        return companies;
    }

   /* public List<CompanyResponse> getCompaniesByAirportID(Long airportID) {
        List<Companies> companies = companiesRepository.findAllByRoute_Id(airportID);
        return companies.stream().map(company ->CompanyResponse.builder()
                .id(company.getId())
                .airportID(company.getId())
                .name(company.getName())
                .build()).toList();


    }*/
}
