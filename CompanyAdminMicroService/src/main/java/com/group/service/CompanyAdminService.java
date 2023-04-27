package com.group.service;

import com.group.dto.request.*;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.exception.CompanyAdminException;
import com.group.exception.EErrorType;
import com.group.manager.ICompanyManager;
import com.group.mapper.IAddressMapper;
import com.group.manager.IAuthManager;
import com.group.mapper.ICompanyAdminMapper;
import com.group.rabbitmq.model.CompanyAdminPasswordModel;
import com.group.rabbitmq.producer.CompanyAdminMailProducer;
import com.group.repository.ICompanyMicroServiceRepository;
import com.group.repository.entity.Address;
import com.group.repository.entity.CompanyAdmin;
import com.group.repository.entity.EStatus;
import com.group.utility.Generator;
import com.group.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyAdminService extends ServiceManager<CompanyAdmin, Long> {

    private final ICompanyMicroServiceRepository companyMicroServiceRepository;
    private final CacheManager cacheManager;
    private final PasswordEncoder passwordEncoder;
    private final CompanyAdminMailProducer companyAdminMailProducer;
    private final ICompanyManager companyManager;
    private final IAuthManager authManager;

    public CompanyAdminService(ICompanyMicroServiceRepository companyMicroServiceRepository, CacheManager cacheManager, PasswordEncoder passwordEncoder, CompanyAdminMailProducer companyAdminMailProducer, ICompanyManager companyManager, IAuthManager authManager) {
        super(companyMicroServiceRepository);
        this.companyMicroServiceRepository = companyMicroServiceRepository;
        this.cacheManager = cacheManager;
        this.passwordEncoder = passwordEncoder;
        this.companyAdminMailProducer = companyAdminMailProducer;
        this.companyManager = companyManager;
        this.authManager = authManager;
    }

    public Boolean register(CompanyAdminRegisterRequestDto dto) {
        if (companyMicroServiceRepository.existsByEmail(dto.getEmail()))
            throw new CompanyAdminException(EErrorType.EMAIL_ALREADY_TAKEN);
        if (companyMicroServiceRepository.existsByIdentity(dto.getIdentity()))
            throw new CompanyAdminException(EErrorType.INVALID_PARAMETER);
        if (companyMicroServiceRepository.existsByPhone(dto.getPhone()))
            throw new CompanyAdminException(EErrorType.INVALID_PARAMETER);
        if (!companyManager.exitsById(dto.getCompanyId()).getBody())
            throw new CompanyAdminException(EErrorType.INVALID_PARAMETER);
        CompanyAdmin companyAdmin = ICompanyAdminMapper.INSTANCE.toCompanyAdmin(dto);
        companyAdmin.setAddress(IAddressMapper.INSTANCE.toAddress(dto.getAddress()));
        String password = Generator.randomPassword();
        companyAdmin.setPassword(passwordEncoder.encode(password));
        RegisterRequestDto registerRequestDto = ICompanyAdminMapper.INSTANCE.toRegisterRequestDto(companyAdmin);
        registerRequestDto.setUserRole("COMPANYADMIN");
        registerRequestDto.setPassword(password);
        Long authId = authManager.register(registerRequestDto).getBody();
        companyAdmin.setAuthId(authId);
        save(companyAdmin);
        companyManager.addPersonal(companyAdmin.getCompanyId());
        companyAdminMailProducer.sendCompanyAdminPassword(CompanyAdminPasswordModel.builder()
                .email(companyAdmin.getEmail()).password(password).build());
        return true;
    }

    public Boolean updateAdmin(CompanyAdminUpdateRequestDto dto) {
        Optional<CompanyAdmin> companyAdmin = findById(dto.getId());
        if (companyMicroServiceRepository.existsByEmail(dto.getEmail()))
            throw new CompanyAdminException(EErrorType.EMAIL_ALREADY_TAKEN);
        if (companyMicroServiceRepository.existsByIdentity(dto.getIdentity()))
            throw new CompanyAdminException(EErrorType.INVALID_PARAMETER);
        if (companyMicroServiceRepository.existsByPhone(dto.getPhone()))
            throw new CompanyAdminException(EErrorType.INVALID_PARAMETER);
        CompanyAdmin toUpdate = companyAdmin.get();
        Address newAddress = IAddressMapper.INSTANCE.toAddress(dto.getAddress());
        cacheManager.getCache("getminor").evict(toUpdate.getId());
        toUpdate.setPhotoUrl(dto.getPhotoUrl());
        toUpdate.setName(dto.getName());
        toUpdate.setSecondName(dto.getSecondName());
        toUpdate.setLastname(dto.getLastname());
        toUpdate.setBirthDate(dto.getBirthDate());
        toUpdate.setBirthPlace(dto.getBirthPlace());
        toUpdate.setIdentity(dto.getIdentity());
        toUpdate.setMajor(dto.getMajor());
        toUpdate.setDepartment(dto.getDepartment());
        toUpdate.setEmail(dto.getEmail());
        toUpdate.setPhone(dto.getPhone());
        toUpdate.setAddress(newAddress);
        update(toUpdate);
        authManager.updateMail(UpdateMailRequestDto.builder().id(toUpdate.getAuthId()).email(toUpdate.getEmail()).build());
        return true;
    }

    public Boolean deleteAdmin(Long id) {
        Optional<CompanyAdmin> companyAdmin = findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        companyAdmin.get().setStatus(EStatus.DELETED);
        update(companyAdmin.get());
        companyManager.deletePersonal(companyAdmin.get().getCompanyId());
        authManager.deleteByAuthId(companyAdmin.get().getAuthId());
        return true;
    }

    @Cacheable(value = "getminor", key = "#id")
    public CompanyAdminResponseDto findCompanyAdminById(Long id) {
        Optional<CompanyAdmin> companyAdmin = companyMicroServiceRepository.findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        return ICompanyAdminMapper.INSTANCE.fromCompanyAdmin(companyAdmin.get());
    }

    public GetAllCompanyAdminDetailsResponseDto getAllDetails(Long id) {
        Optional<CompanyAdmin> companyAdmin = companyMicroServiceRepository.findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        GetAllCompanyAdminDetailsResponseDto details = ICompanyAdminMapper.INSTANCE.fromCompanyAdminToGetAllDetails(companyAdmin.get());
        return details;
    }

    public List<CompanyAdminResponseDto> getAllCompanyAdmins() {
        return findAll().stream().map(x -> ICompanyAdminMapper.INSTANCE.fromCompanyAdmin(x)).toList();
    }

    public Boolean deActivateById(Long id) {
        Optional<CompanyAdmin> companyAdmin = findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        companyAdmin.get().setStatus(EStatus.NOT_ACTIVE);
        update(companyAdmin.get());
        authManager.deleteByAuthId(companyAdmin.get().getAuthId());
        return true;
    }
    public Boolean hardDeleteById(Long id) {
        Optional<CompanyAdmin> companyAdmin = findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.INVALID_PARAMETER);
        deleteById(id);
        companyManager.deletePersonal(companyAdmin.get().getCompanyId());
        authManager.deleteByAuthId(companyAdmin.get().getAuthId());
        return true;
    }
    public Boolean updatePassword(UpdateCompanyAdminPasswordRequestDto dto) {
        Optional<CompanyAdmin> companyAdmin = findById(dto.getId());
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.INVALID_PARAMETER);
        if(passwordEncoder.matches(companyAdmin.get().getPassword(),dto.getPassword()))
            throw new CompanyAdminException(EErrorType.METHOD_NOT_VALID_ARGUMENT_ERROR);
        companyAdmin.get().setPassword(passwordEncoder.encode(dto.getPassword()));
        update(companyAdmin.get());
        authManager.updatePassword(UpdatePasswordRequestDto.builder().id(companyAdmin.get().getAuthId()).password(dto.getPassword()).build());
        return true;
    }
}
