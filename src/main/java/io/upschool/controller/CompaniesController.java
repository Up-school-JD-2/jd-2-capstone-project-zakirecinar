package io.upschool.controller;

import io.upschool.dto.CompanyRequest;
import io.upschool.dto.CompanyResponse;
import io.upschool.dto.CompanySearchDto;
import io.upschool.entity.Companies;
import io.upschool.services.CompaniesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompaniesController {
    private final CompaniesServices companiesServices;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getCompanies(){
        var companies=companiesServices.getAllCompanies();
        return ResponseEntity.ok(companies);
        //badrequest olursa bir veri dönmeyiz içine direkt hata verir
    }
    @PostMapping
    public Companies createAirport(@RequestBody CompanyRequest companyRequest){
        return companiesServices.save(companyRequest);
    }
    @PostMapping("/airportID")
    public ResponseEntity<CompanyResponse> getCompaniesByID(@RequestBody CompanySearchDto companySearchDto){
        return ResponseEntity.ok(companiesServices.getCompanyById(companySearchDto));
    }
    /*@GetMapping("{id}")
    public ResponseEntity<List<CompanyResponse>> getCompaniesByAirportID(@PathVariable("id") Long id) {
        return ResponseEntity.ok(companiesServices.getCompaniesByAirportID(id));
    }*/

}
