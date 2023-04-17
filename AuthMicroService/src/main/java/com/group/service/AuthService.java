package com.group.service;


import com.group.dto.request.UpdatePasswordRequestDto;
import com.group.dto.response.FindByIdResponseDto;
import com.group.exception.AuthServiceException;
import com.group.exception.EErrorType;

import com.group.dto.request.RegisterRequestDto;
import com.group.manager.IAdminManager;
import com.group.mapper.IAuthMapper;

import com.group.rabbitmq.model.ActivateStatusModel;
import com.group.rabbitmq.producer.RegisterMailProducer;
import com.group.repository.IAuthRepository;
import com.group.repository.entity.Auth;
import com.group.repository.entity.EStatus;
import com.group.utility.Generator;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;
    private final RegisterMailProducer registerMailProducer;
    private final IAdminManager adminManager;

    public AuthService(IAuthRepository authRepository, RegisterMailProducer registerMailProducer, IAdminManager adminManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.registerMailProducer = registerMailProducer;
        this.adminManager = adminManager;
    }


    public Boolean updatePassword(UpdatePasswordRequestDto dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        auth.get().setPassword(dto.getPassword());
        update(auth.get());
        return true;
    }
    public Boolean register(RegisterRequestDto dto) {
        if (authRepository.existsByEmail(dto.getEmail()))
            throw new AuthServiceException(EErrorType.EMAIL_ALREADY_TAKEN);
        try {
            registerMailProducer.sendActivationCode(ActivateStatusModel.builder()
                    .activationCode(Generator.randomActivationCode()).email(dto.getEmail()).build());
        }catch (Exception exception){
            throw new AuthServiceException(EErrorType.INVALID_PARAMETER);
        }
        Auth auth = save(IAuthMapper.INSTANCE.toAuth(dto));
        adminManager.save(IAuthMapper.INSTANCE.toSaveRequestDto(auth));
        return true;
    }
    public FindByIdResponseDto findByIdResponseDto(Long id){
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        return  IAuthMapper.INSTANCE.fromAuth(auth.get());
    }

    public Boolean deactivateById(Long id) {
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        auth.get().setStatus(EStatus.NOT_ACTIVE);
        update(auth.get());
        return true;
    }
    public Boolean deleteByAuthId(Long id) {
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        deleteById(id);
        return true;
    }
}
