package com.project.hunter.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.domain.dto.companies.CompanyDto;
import com.project.hunter.domain.dto.companies.CreateCompanyDto;
import com.project.hunter.services.CompanyService;



@RestController
@RequestMapping("companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping()
    public ResponseEntity<CompanyDto> createCompanyApi(
            @RequestBody CreateCompanyDto createCompanyDto) {
        CompanyDto createdCompanyDto = this.companyService.handleCreateCompany(createCompanyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompanyDto);
    }

    @GetMapping()
    public ResponseEntity<List<CompanyDto>> getAllCompanyApi() {
        List<CompanyDto> companies = this.companyService.handleGetAllCompany();
        return ResponseEntity.ok().body(companies);
    }
    

}
