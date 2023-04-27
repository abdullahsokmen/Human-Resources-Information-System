package com.group.service;




import com.group.dto.request.ActivateRequestDto;

import com.group.dto.request.LoginRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.request.UpdatePasswordRequestDto;
import com.group.dto.response.FindByIdResponseDto;
import com.group.dto.response.LoginResponse;
import com.group.exception.AuthManagerException;
import com.group.exception.EErrorType;

import com.group.mapper.IAuthMapper;

import com.group.repository.IAuthRepository;
import com.group.repository.entity.Auth;
import com.group.repository.entity.ERole;
import com.group.repository.entity.EStatus;
import com.group.utility.Generator;
import com.group.utility.JwtTokenManager;
import com.group.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IAuthRepository authRepository,
                       JwtTokenManager jwtTokenManager, PasswordEncoder passwordEncoder) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean deactivateById(Long id) {
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty())
            throw new AuthManagerException(EErrorType.USER_NOT_FOUND);
        auth.get().setStatus(EStatus.NOT_ACTIVE);
        update(auth.get());
        return true;
    }
    public Boolean deleteByAuthId(Long id) {
        Optional<Auth> auth = findById(id);
        if (auth.isEmpty())
            throw new AuthManagerException(EErrorType.USER_NOT_FOUND);
        deleteById(id);
        return true;
    }

    public LoginResponse doLogin(LoginRequestDto dto) {
        Optional<Auth> optionalAuth= authRepository.findByEmail(dto.getEmail());
        if (optionalAuth.isEmpty())
            throw new AuthManagerException(EErrorType.USER_NOT_FOUND);
        if(!passwordEncoder.matches(dto.getPassword(), optionalAuth.get().getPassword()))
            throw  new AuthManagerException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
        Auth auth = optionalAuth.get();
        Optional<String> token = jwtTokenManager.createToken(auth.getId(), auth.getRole());
        if (token.isEmpty())
            throw new AuthManagerException(EErrorType.TOKEN_NOT_CREATED);
        return LoginResponse.builder().id(auth.getId()).token(token.get()).role(auth.getRole().name()).build();
    }

    public Boolean register(RegisterRequestDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        auth.setPassword(passwordEncoder.encode(dto.getPassword()));
        auth.setRole(ERole.valueOf(dto.getUserRole()));
        save(auth);
        return true;
    }
}

