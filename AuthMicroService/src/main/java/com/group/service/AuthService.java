package com.group.service;


import com.group.dto.request.UpdatePasswordRequestDto;
import com.group.exception.AuthServiceException;
import com.group.exception.EErrorType;

import com.group.dto.request.RegisterRequestDto;
import com.group.mapper.IAuthMapper;

import com.group.repository.IAuthRepository;
import com.group.repository.entity.Auth;
import com.group.repository.entity.EStatus;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;

    public AuthService(IAuthRepository authRepository) {
        super(authRepository);
        this.authRepository = authRepository;
    }


    public Boolean updatePassword(UpdatePasswordRequestDto dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        auth.get().setPassword(dto.getPassword());
        update(auth.get());
        return true;
    }
    public Auth saveDto(RegisterRequestDto dto) {
        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);
        return save(auth);

    }

    public Boolean deactivateById(Long id) {
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        auth.get().setStatus(EStatus.DELETED);
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
