package com.group.service;

import com.group.repository.ICompanyMicroServiceRepository;
import com.group.repository.entity.CompanyAdmin;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CompanyAdminService extends ServiceManager<CompanyAdmin,Long> {

    private final ICompanyMicroServiceRepository companyMicroServiceRepository;

    public CompanyAdminService(ICompanyMicroServiceRepository companyMicroServiceRepository) {
        super(companyMicroServiceRepository);
        this.companyMicroServiceRepository = companyMicroServiceRepository;
    }
}
