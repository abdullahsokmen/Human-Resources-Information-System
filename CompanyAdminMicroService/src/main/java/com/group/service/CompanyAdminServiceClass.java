package com.group.service;

import com.group.dto.request.CompanyAdminUpdateRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.exception.CompanyAdminException;
import com.group.exception.EErrorType;
import com.group.mapper.ICompanyAdminMapper;
import com.group.rabbitmq.model.ActivateStatusModel;
import com.group.rabbitmq.producer.RegisterMailProducer;
import com.group.repository.ICompanyMicroServiceRepository;
import com.group.repository.entity.CompanyAdmin;
import com.group.utility.Generator;
import com.group.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyAdminServiceClass extends ServiceManager<CompanyAdmin,Long> {

    private final ICompanyMicroServiceRepository companyMicroServiceRepository;
    private final CacheManager cacheManager;
    private final RegisterMailProducer registerMailProducer;

    public CompanyAdminServiceClass(ICompanyMicroServiceRepository companyMicroServiceRepository, CacheManager cacheManager, RegisterMailProducer registerMailProducer) {
        super(companyMicroServiceRepository);
        this.companyMicroServiceRepository = companyMicroServiceRepository;
        this.cacheManager = cacheManager;
        this.registerMailProducer = registerMailProducer;
    }

    public Boolean register(RegisterRequestDto dto) {
        if (companyMicroServiceRepository.existsByEmail(dto.getEmail()))
            throw new CompanyAdminException(EErrorType.EMAIL_ALREADY_TAKEN);
        CompanyAdmin companyAdmin= ICompanyAdminMapper.INSTANCE.toCompanyAdmin(dto);
        String activationCode= Generator.randomActivationCode();
        companyAdmin.setActivationCode(activationCode);
        save(companyAdmin);
        try {
            registerMailProducer.sendActivationCode(ActivateStatusModel.builder()
                            .activationCode(activationCode).email(dto.getEmail())
                    .build());
        }catch (Exception e){
            throw new CompanyAdminException(EErrorType.MAIL_SEND_ERROR);
        }
        return true;
    }

    public Boolean updateAdmin(CompanyAdminUpdateRequestDto dto) {
        Optional<CompanyAdmin>companyAdmin=findById(dto.getId());
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        if (companyMicroServiceRepository.existsByEmail(dto.getEmail()))
            throw new CompanyAdminException(EErrorType.EMAIL_ALREADY_TAKEN);
        CompanyAdmin toUpdate=companyAdmin.get();
        cacheManager.getCache("getminor").evict(toUpdate.getId());
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

    @Cacheable(value = "getminor", key = "#id")
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
