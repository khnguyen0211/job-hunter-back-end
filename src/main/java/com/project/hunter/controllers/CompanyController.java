package com.project.hunter.controllers;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hunter.domain.dto.companies.CompanyDto;
import com.project.hunter.domain.dto.companies.CreateCompanyDto;
import com.project.hunter.domain.dto.companies.UpdateCompanyDto;
import com.project.hunter.domain.dto.pagination.PageResult;
import com.project.hunter.domain.entities.CompanyEntity;
import com.project.hunter.exceptions.IdInvalidException;
import com.project.hunter.exceptions.NotFoundException;
import com.project.hunter.services.CompanyService;
import com.project.hunter.utils.UUIDChecker;
import com.turkraft.springfilter.boot.Filter;

import jakarta.validation.Valid;



@RestController
@RequestMapping("api/v1/companies")
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
    public ResponseEntity<PageResult<CompanyDto>> getAllCompanyApi(
            @Filter Specification<CompanyEntity> spec, Pageable pageable) {
        PageResult<CompanyDto> pageResultCompanies =
                this.companyService.handleGetAllCompany(spec, pageable);
        return ResponseEntity.ok().body(pageResultCompanies);
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDto> updateCompanyApi(@PathVariable("id") String id,
            @RequestBody UpdateCompanyDto updateCompanyDto)
            throws IdInvalidException, NotFoundException {
        if (!UUIDChecker.isValidUUID(id)) {
            throw new IdInvalidException("CompanyId must be UUID format");
        }
        UUID uuid = UUID.fromString(id);
        CompanyDto companyDto = this.companyService.handleUpdateCompany(uuid, updateCompanyDto);
        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompanyDto> deleteCompanyApi(@Valid @PathVariable("id") String id)
            throws IdInvalidException, NotFoundException {
        if (!UUIDChecker.isValidUUID(id)) {
            throw new IdInvalidException("CompanyId must be UUID format");
        }
        UUID uuid = UUID.fromString(id);
        CompanyDto companyDto = this.companyService.handleDeleteCompany(uuid);
        return ResponseEntity.ok(companyDto);
    }
}
