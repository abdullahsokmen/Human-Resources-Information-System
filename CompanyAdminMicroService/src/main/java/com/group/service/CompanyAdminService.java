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
import com.group.repository.ICompanyAdminRepository;
import com.group.repository.entity.Address;
import com.group.repository.entity.CompanyAdmin;
import com.group.repository.entity.EStatus;
import com.group.utility.Generator;
import com.group.utility.JwtTokenManager;
import com.group.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyAdminService extends ServiceManager<CompanyAdmin, Long> {

    private final ICompanyAdminRepository companyAdminRepository;
    private final CacheManager cacheManager;
    private final PasswordEncoder passwordEncoder;
    private final CompanyAdminMailProducer companyAdminMailProducer;
    private final ICompanyManager companyManager;
    private final IAuthManager authManager;
    private final JwtTokenManager tokenManager;

    public CompanyAdminService(ICompanyAdminRepository companyAdminRepository, CacheManager cacheManager, PasswordEncoder passwordEncoder, CompanyAdminMailProducer companyAdminMailProducer, ICompanyManager companyManager, IAuthManager authManager, JwtTokenManager tokenManager) {
        super(companyAdminRepository);
        this.companyAdminRepository = companyAdminRepository;
        this.cacheManager = cacheManager;
        this.passwordEncoder = passwordEncoder;
        this.companyAdminMailProducer = companyAdminMailProducer;
        this.companyManager = companyManager;
        this.authManager = authManager;
        this.tokenManager = tokenManager;
    }

    public Boolean register(CompanyAdminRegisterRequestDto dto) {
        if (companyAdminRepository.existsByEmail(dto.getEmail()))
            throw new CompanyAdminException(EErrorType.EMAIL_ALREADY_TAKEN);
        if (companyAdminRepository.existsByIdentity(dto.getIdentity()))
            throw new CompanyAdminException(EErrorType.IDENTITY_ALREADY_EXIST);
        if (companyAdminRepository.existsByPhone(dto.getPhone()))
            throw new CompanyAdminException(EErrorType.PHONE_ALREADY_TAKEN);
        if (!companyManager.exitsById(dto.getCompanyId()).getBody())
            throw new CompanyAdminException(EErrorType.COMPANY_NOT_FOUND);
        CompanyAdmin companyAdmin = ICompanyAdminMapper.INSTANCE.toCompanyAdmin(dto);
        companyAdmin.setAddress(IAddressMapper.INSTANCE.toAddress(dto.getAddress()));
        String password = Generator.randomPassword();
        companyAdmin.setPassword(passwordEncoder.encode(password));
        RegisterRequestDto registerRequestDto = ICompanyAdminMapper.INSTANCE.toRegisterRequestDto(companyAdmin);
        registerRequestDto.setUserRole("COMPANYADMIN");
        registerRequestDto.setPassword(password);
        registerRequestDto.setSurname(dto.getLastname());
        Long authId = authManager.register(registerRequestDto).getBody();
        companyAdmin.setAuthId(authId);
        companyAdmin.setDateOfStart(new Date());
        save(companyAdmin);
        companyManager.addPersonal(companyAdmin.getCompanyId());
        companyAdminMailProducer.sendCompanyAdminPassword(CompanyAdminPasswordModel.builder()
                .email(companyAdmin.getEmail()).password(password).build());
        return true;
    }

    public Boolean updateAdmin(CompanyAdminUpdateRequestDto dto) {
        Optional<CompanyAdmin> companyAdmin = findById(dto.getId());
        if (companyAdminRepository.existsByEmail(dto.getEmail()))
            throw new CompanyAdminException(EErrorType.EMAIL_ALREADY_TAKEN);
        if (companyAdminRepository.existsByIdentity(dto.getIdentity()))
            throw new CompanyAdminException(EErrorType.IDENTITY_ALREADY_EXIST);
        if (companyAdminRepository.existsByPhone(dto.getPhone()))
            throw new CompanyAdminException(EErrorType.PHONE_ALREADY_TAKEN);
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
        authManager.deactivateById(companyAdmin.get().getAuthId());
        return true;
    }

    @Cacheable(value = "getminor", key = "#id")
    public CompanyAdminResponseDto findCompanyAdminById(Long id) {
        Optional<CompanyAdmin> companyAdmin = companyAdminRepository.findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        return ICompanyAdminMapper.INSTANCE.fromCompanyAdmin(companyAdmin.get());
    }

    public GetAllCompanyAdminDetailsResponseDto getAllDetails(Long id) {
        Optional<CompanyAdmin> companyAdmin = companyAdminRepository.findById(id);
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
        authManager.deactivateById(companyAdmin.get().getAuthId());
        return true;
    }
    public Boolean hardDeleteById(Long id) {
        Optional<CompanyAdmin> companyAdmin = findById(id);
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        deleteById(id);
        companyManager.deletePersonal(companyAdmin.get().getCompanyId());
        authManager.deleteByAuthId(companyAdmin.get().getAuthId());
        return true;
    }

    public Boolean updateCompanyAdminPassword(CompanyAdminUpdatePasswordRequestDto dto) {
        Optional<CompanyAdmin>companyAdmin=findById(dto.getId());
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        if (passwordEncoder.matches(companyAdmin.get().getPassword(),dto.getCurrentPassword()))
            throw new CompanyAdminException(EErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);
        companyAdmin.get().setPassword(passwordEncoder.encode(dto.getPassword()));
        update(companyAdmin.get());
        authManager.updatePassword(UpdatePasswordRequestDto.builder().id(companyAdmin.get().getAuthId()).password(dto.getPassword()).build());
        return true;
    }

    public void resetPassword(ResetPasswordRequestDto dto) {
        Optional<CompanyAdmin> companyAdmin = companyAdminRepository.findByAuthId(dto.getAuthId());
        if (companyAdmin.isEmpty())
            throw new CompanyAdminException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        companyAdmin.get().setPassword(passwordEncoder.encode(dto.getPassword()));
        update(companyAdmin.get());
    }
}
