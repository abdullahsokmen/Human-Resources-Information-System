package com.group.service;

import com.group.dto.request.RegisterRequestDto;
import com.group.exception.CompanyAdminException;
import com.group.exception.EErrorType;
import com.group.mapper.ICompanyAdminMapper;
import com.group.repository.ICompanyMicroServiceRepository;
import com.group.repository.entity.CompanyAdmin;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class CompanyAdminServiceClass extends ServiceManager<CompanyAdmin,Long> {

    private final ICompanyMicroServiceRepository companyMicroServiceRepository;

    public CompanyAdminServiceClass(ICompanyMicroServiceRepository companyMicroServiceRepository) {
        super(companyMicroServiceRepository);
        this.companyMicroServiceRepository = companyMicroServiceRepository;
    }

    public Boolean register(RegisterRequestDto dto) {
        if (companyMicroServiceRepository.existsByEmail(dto.getEmail()))
            throw new CompanyAdminException(EErrorType.EMAIL_ALREADY_TAKEN);
        CompanyAdmin companyAdmin= ICompanyAdminMapper.INSTANCE.toCompanyAdmin(dto);
        save(companyAdmin);
        return true;
    }
}
