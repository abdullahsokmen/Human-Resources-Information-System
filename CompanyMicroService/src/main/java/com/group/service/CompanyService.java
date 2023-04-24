package com.group.service;

import com.group.repository.ICompanyRepository;
import com.group.repository.entity.Company;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends ServiceManager<Company,String > {
    private final ICompanyRepository companyRepository;

    public CompanyService(ICompanyRepository companyRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
    }
}
