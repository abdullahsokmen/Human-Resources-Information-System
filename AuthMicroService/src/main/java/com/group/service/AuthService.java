package com.group.service;

import com.group.repository.IAuthRepository;
import com.group.repository.entity.Auth;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;

    public AuthService(IAuthRepository authRepository) {
        super(authRepository);
        this.authRepository = authRepository;
    }
}
