package com.group.service;


import com.group.dto.request.EditProfileRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.request.SaveRequestDto;
import com.group.dto.response.GetAllResponseDto;
import com.group.dto.response.GetMinorInfoResponseDto;


import com.group.dto.request.UpdateRequestDto;

import com.group.exception.AdminServiceException;
import com.group.exception.EErrorType;
import com.group.manager.IAuthManager;
import com.group.mapper.IAddressMapper;
import com.group.mapper.IAdminMapper;
import com.group.rabbitmq.model.AdminPasswordSenderModel;
import com.group.rabbitmq.producer.AdminPasswordProducer;
import com.group.repository.entity.Admin;
import com.group.repository.IAdminRepository;
import com.group.utility.Generator;
import com.group.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin,Long> {
    private final IAdminRepository adminRepository;
    private final AdminPasswordProducer adminPasswordProducer;
    private final IAuthManager authManager;

    private final PasswordEncoder passwordEncoder;

    public AdminService(IAdminRepository adminRepository, AdminPasswordProducer adminPasswordProducer, IAuthManager authManager, PasswordEncoder passwordEncoder) {
        super(adminRepository);
        this.adminRepository=adminRepository;
        this.adminPasswordProducer = adminPasswordProducer;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
    }



    public GetAllResponseDto getAllDetail(Long id) {
        Optional<Admin> admin = findById(id);
        if (admin.isEmpty())
            throw new AdminServiceException(EErrorType.ADMIN_NOT_FOUND);
        GetAllResponseDto dto = IAdminMapper.INSTANCE.toGetAllResponseDto(admin.get());
        dto.setCreateAt(new Date(admin.get().getCreateDate()));
        return dto;
    }


    public GetMinorInfoResponseDto getMinorInformation(Long id) {
        Optional<Admin> admin = findById(id);
        if (admin.isEmpty())
            throw new AdminServiceException(EErrorType.ADMIN_NOT_FOUND);
        return IAdminMapper.INSTANCE.fromAdmin(admin.get());
    }

    public Boolean updateDto(UpdateRequestDto dto){
            Optional<Admin> admin = adminRepository.findById(dto.getId());
            if (admin.isEmpty())
                throw new AdminServiceException(EErrorType.ADMIN_NOT_FOUND);
            admin.get().setAddress(IAddressMapper.INSTANCE.toAddress(dto.getAddress()));
            admin.get().setPhotoUrl(dto.getPhotoUrl());
            admin.get().setPhone(dto.getPhone());
            update(admin.get());
            return true;
    }
    public Boolean editProfile(EditProfileRequestDto dto){
        Optional<Admin> admin=findById(dto.getId());
        if (admin.isEmpty())
            throw new AdminServiceException(EErrorType.ADMIN_NOT_FOUND);
        admin.get().setBirthDate(dto.getBirthDate());
        admin.get().setBirthPlace(dto.getBirthPlace());
        admin.get().setIdentity(dto.getIdentity());
        admin.get().setPhone(dto.getPhone());
        update(admin.get());
        return true;
    }

    public Boolean createAdmin(SaveRequestDto dto) {
        if (adminRepository.existsByEmail(dto.getEmail()))
            throw new AdminServiceException(EErrorType.EMAIL_ALREADY_TAKEN);
        Admin admin = IAdminMapper.INSTANCE.toAdmin(dto);
        String password = Generator.randomPassword();
        admin.setPassword(passwordEncoder.encode(password));
        RegisterRequestDto register = IAdminMapper.INSTANCE.toRegisterRequestDto(admin);
        register.setPassword(password);
        register.setUserRole("ADMIN");
        Long authId = authManager.register(register).getBody();
        admin.setAuthId(authId);
        save(admin);
        adminPasswordProducer.sendAdminPassword(AdminPasswordSenderModel.builder().email(admin.getEmail()).password(password).build());

        return true;
    }

    public Boolean deleteAdminById(Long id) {
        Optional<Admin> admin=findById(id);
        if (admin.isEmpty())
            throw new AdminServiceException(EErrorType.ADMIN_NOT_FOUND);
        authManager.deleteByAuthId(admin.get().getAuthId());
        deleteById(id);
        return true;
    }
}
