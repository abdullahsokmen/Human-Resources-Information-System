package com.group.service;

import com.group.dto.request.CompanySaveRequestDto;
import com.group.dto.request.CompanyUpdateRequestDto;
import com.group.dto.response.CompanyResponseDto;
import com.group.dto.response.GetAllCompanyDetailsResponseDto;
import com.group.exception.CompanyManagerException;
import com.group.exception.EErrorType;
import com.group.mapper.ICompanyMapper;
import com.group.repository.ICompanyRepository;
import com.group.repository.entity.Company;
import com.group.repository.entity.EStatus;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService extends ServiceManager<Company,String > {
    private final ICompanyRepository companyRepository;

    public CompanyService(ICompanyRepository companyRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
    }

    public Boolean saveCompany(CompanySaveRequestDto dto) {
        Company company = ICompanyMapper.INSTANCE.toCompany(dto);
        if(companyRepository.existsByEmail(dto.getEmail()))
            throw new CompanyManagerException(EErrorType.REGISTER_ERROR_COMPANYEMAIL);
        if(companyRepository.existsByCompanyName(dto.getCompanyName()))
            throw new CompanyManagerException(EErrorType.REGISTER_ERROR_COMPANYNAME);
        companyRepository.save(company);
        return true;
    }

    public Boolean updateCompany(CompanyUpdateRequestDto dto) {
        Optional<Company> company = companyRepository.findById(dto.getId());
        if(company.isEmpty())
            throw new CompanyManagerException(EErrorType.COMPANY_NOT_FOUND);
        if(companyRepository.existsByEmail(dto.getEmail()))
            throw new CompanyManagerException(EErrorType.REGISTER_ERROR_COMPANYEMAIL);
        if(companyRepository.existsByCompanyName(dto.getCompanyName()))
            throw new CompanyManagerException(EErrorType.REGISTER_ERROR_COMPANYNAME);
        Company toUpdate = company.get();
        toUpdate.setCompanyName(dto.getCompanyName());
        toUpdate.setEmail(dto.getEmail());
        toUpdate.setTitle(dto.getTitle());
        toUpdate.setTaxOffice(dto.getTaxOffice());
        toUpdate.setLogoUrl(dto.getLogoUrl());
        toUpdate.setPhone(dto.getPhone());
        toUpdate.setAddress(dto.getAddress());
        toUpdate.setContractEndDate(dto.getContractEndDate());
        update(toUpdate);
        return true;
    }


    public Boolean deleteCompany(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isEmpty())
            throw new CompanyManagerException(EErrorType.COMPANY_NOT_FOUND);
        deleteById(id);
        return true;
    }

    public CompanyResponseDto findCompanyById(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isEmpty())
            throw new CompanyManagerException(EErrorType.COMPANY_NOT_FOUND);
        return  ICompanyMapper.INSTANCE.fromCompany(company.get());
    }

    public Boolean deactivateCompany(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isEmpty())
            throw new CompanyManagerException(EErrorType.COMPANY_NOT_FOUND);
        company.get().setStatus(EStatus.NOT_ACTIVE);
        update(company.get());
        return true;
    }

    public GetAllCompanyDetailsResponseDto getAllDetails (String id) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isEmpty())
            throw new CompanyManagerException(EErrorType.COMPANY_NOT_FOUND);
        GetAllCompanyDetailsResponseDto allDetails = ICompanyMapper.INSTANCE.fromCompanyToGetAllDetails(company.get());
        allDetails.setPosition(company.get().getStatus().name());
        return  allDetails;
    }

    public List<CompanyResponseDto> getAllCompanies() {
        return findAll().stream().map(x -> ICompanyMapper.INSTANCE.fromCompany(x)).toList();
    }
}
