package com.project.hunter.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.hunter.domain.dto.companies.CompanyDto;
import com.project.hunter.domain.dto.companies.CreateCompanyDto;
import com.project.hunter.domain.dto.pagination.MetaData;
import com.project.hunter.domain.dto.pagination.PageResult;
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

    public PageResult<CompanyDto> handleGetAllCompany(Specification<CompanyEntity> spec,
            Pageable pageable) {
        Page<CompanyEntity> pageCompany = this.companyRepository.findAll(spec, pageable);
        MetaData meta = new MetaData(pageCompany.getNumber() + 1, pageCompany.getSize(),
                pageCompany.getTotalPages(), pageCompany.getTotalElements());
        List<CompanyDto> companyDtoList = pageCompany.getContent().stream()
                .map(companyEntity -> new CompanyDto(companyEntity)).collect(Collectors.toList());
        PageResult<CompanyDto> pageResultCompany = new PageResult<>(meta, companyDtoList);
        return pageResultCompany;
    }

}
