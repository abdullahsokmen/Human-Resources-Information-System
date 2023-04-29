package com.group.service;




import com.group.dto.request.*;

import com.group.dto.response.LoginResponse;
import com.group.exception.AuthManagerException;
import com.group.exception.EErrorType;

import com.group.manager.IAdminManager;
import com.group.manager.ICompanyAdminManager;
import com.group.manager.IPersonalManager;
import com.group.mapper.IAuthMapper;

import com.group.rabbitmq.model.ResetPasswordModel;
import com.group.rabbitmq.producer.ResetPasswordProducer;
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
    private final IAdminManager adminManager;
    private final ICompanyAdminManager companyAdminManager;
    private final IPersonalManager personalManager;
    private final ResetPasswordProducer resetPasswordProducer;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, PasswordEncoder passwordEncoder,
                       IAdminManager adminManager, ICompanyAdminManager companyAdminManager,
                       IPersonalManager personalManager, ResetPasswordProducer resetPasswordProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.passwordEncoder = passwordEncoder;
        this.adminManager = adminManager;
        this.companyAdminManager = companyAdminManager;
        this.personalManager = personalManager;
        this.resetPasswordProducer = resetPasswordProducer;
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
        if (optionalAuth.isEmpty() || !passwordEncoder.matches(dto.getPassword(), optionalAuth.get().getPassword()))
            throw  new AuthManagerException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
        Auth auth = optionalAuth.get();
        Optional<String> token = jwtTokenManager.createToken(auth.getId(), auth.getRole());
        if (token.isEmpty())
            throw new AuthManagerException(EErrorType.TOKEN_NOT_CREATED);
        return LoginResponse.builder().id(auth.getId()).token(token.get()).role(auth.getRole().name()).build();
    }

    public Long register(RegisterRequestDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        auth.setPassword(passwordEncoder.encode(dto.getPassword()));
        auth.setRole(ERole.valueOf(dto.getUserRole()));
        save(auth);
        return auth.getId();
    }

    public Boolean updatePassword(UpdatePasswordRequestDto dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty())
            throw new AuthManagerException(EErrorType.USER_NOT_FOUND);
        auth.get().setPassword(passwordEncoder.encode(dto.getPassword()));
        update(auth.get());
        return true;
    }

    public Boolean updateMail(UpdateMailRequestDto dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty())
            throw new AuthManagerException(EErrorType.USER_NOT_FOUND);
        auth.get().setEmail(dto.getEmail());
        update(auth.get());
        return true;
    }

    public Boolean resetPassword(String email) {
        Optional<Auth> optionalAuth = authRepository.findByEmail(email);
        if (optionalAuth.isEmpty())
            throw new AuthManagerException(EErrorType.USER_NOT_FOUND);
        Auth auth = optionalAuth.get();
        String password = Generator.randomPassword();

        try {
            resetPasswordProducer.sendNewPassword(ResetPasswordModel.builder().email(email).password(password).build());
        }catch (Exception exception){
            throw new AuthManagerException(EErrorType.MAIL_SEND_ERROR);
        }
        auth.setPassword(passwordEncoder.encode(password));
        update(auth);

        switch (optionalAuth.get().getRole()){
            case ADMIN: adminManager.resetPassword(ResetPasswordRequestDto.builder()
                    .authId(auth.getId()).password(password).build()); break;
            case COMPANYADMIN: companyAdminManager.resetPassword(ResetPasswordRequestDto.builder()
                    .authId(auth.getId()).password(password).build()); break;
            case PERSONAL: personalManager.resetPassword(ResetPasswordRequestDto.builder()
                    .authId(auth.getId()).password(password).build()); break;
        }

        return true;
    }
}

