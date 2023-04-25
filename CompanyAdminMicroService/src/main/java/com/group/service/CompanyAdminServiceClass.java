package com.group.service;

import com.group.dto.request.CompanyAdminUpdateRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.exception.CompanyAdminException;
import com.group.exception.EErrorType;
import com.group.mapper.ICompanyAdminMapper;
import com.group.repository.ICompanyMicroServiceRepository;
import com.group.repository.entity.CompanyAdmin;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Boolean updateAdmin(CompanyAdminUpdateRequestDto dto) {
        Optional<CompanyAdmin>companyAdmin=findById(dto.getId());
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        if (companyMicroServiceRepository.existsByEmail(dto.getEmail()))
            throw new CompanyAdminException(EErrorType.EMAIL_ALREADY_TAKEN);
        CompanyAdmin toUpdate=companyAdmin.get();
        toUpdate.setPhotoUrl(dto.getPhotoUrl());
        toUpdate.setName(dto.getName());
        toUpdate.setSecondName(dto.getSecondName());
        toUpdate.setLastname(dto.getLastname());
        toUpdate.setBirthDate(dto.getBirthDate());
        toUpdate.setDateOfStart(dto.getDateOfStart());
        toUpdate.setMajor(dto.getMajor());
        toUpdate.setDepartment(dto.getDepartment());
        toUpdate.setEmail(dto.getEmail());
        toUpdate.setPhone(dto.getPhone());
        toUpdate.setCompanyName(dto.getCompanyName());
        update(toUpdate);
        return true;
    }

    public Boolean deleteAdmin(Long id) {
        Optional<CompanyAdmin>companyAdmin=findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        deleteById(id);
        return true;
    }

    public CompanyAdminResponseDto findCompanyAdminById(Long id) {
        Optional<CompanyAdmin>companyAdmin=companyMicroServiceRepository.findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        return ICompanyAdminMapper.INSTANCE.fromCompanyAdmin(companyAdmin.get());
    }

    public GetAllCompanyAdminDetailsResponseDto getAllDetails(Long id) {
        Optional<CompanyAdmin>companyAdmin=companyMicroServiceRepository.findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        GetAllCompanyAdminDetailsResponseDto details=ICompanyAdminMapper.INSTANCE.fromCompanyAdminToGetAllDetails(companyAdmin.get());
        return details;
    }

    public List<CompanyAdminResponseDto> getAllCompanyAdmins() {
        return findAll().stream().map(x->ICompanyAdminMapper.INSTANCE.fromCompanyAdmin(x)).toList();
    }
}
