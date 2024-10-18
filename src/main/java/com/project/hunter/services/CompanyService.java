package com.project.hunter.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.hunter.domain.dto.companies.CompanyDto;
import com.project.hunter.domain.dto.companies.CreateCompanyDto;
import com.project.hunter.domain.entities.CompanyEntity;
import com.project.hunter.repositories.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDto handleCreateCompany(CreateCompanyDto createCompanyDto) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(createCompanyDto.getName());
        companyEntity.setDescription(createCompanyDto.getDescription());
        companyEntity.setLogo(createCompanyDto.getLogo());
        companyEntity.setAddress(createCompanyDto.getAddress());
        return new CompanyDto(this.companyRepository.save(companyEntity));
    }

    public List<CompanyDto> handleGetAllCompany() {
        List<CompanyEntity> companyEntities = this.companyRepository.findAll();
        return companyEntities.stream().map(companyEntity -> new CompanyDto(companyEntity))
                .collect(Collectors.toList());
    }

}
