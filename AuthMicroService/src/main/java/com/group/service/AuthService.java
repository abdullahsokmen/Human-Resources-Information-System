package com.group.service;




import com.group.dto.request.ActivateRequestDto;

import com.group.dto.request.LoginRequestDto;
import com.group.dto.request.UpdatePasswordRequestDto;
import com.group.dto.response.FindByIdResponseDto;
import com.group.dto.response.LoginResponse;
import com.group.exception.AuthServiceException;
import com.group.exception.EErrorType;

import com.group.dto.request.RegisterRequestDto;
import com.group.manager.IAdminManager;
import com.group.mapper.IAuthMapper;

import com.group.rabbitmq.model.ActivateStatusModel;
import com.group.rabbitmq.producer.RegisterMailProducer;
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
    private final RegisterMailProducer registerMailProducer;
    private final IAdminManager adminManager;
    private final JwtTokenManager jwtTokenManager;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IAuthRepository authRepository, RegisterMailProducer registerMailProducer,
                       IAdminManager adminManager, JwtTokenManager jwtTokenManager, PasswordEncoder passwordEncoder) {
        super(authRepository);
        this.authRepository = authRepository;
        this.registerMailProducer = registerMailProducer;
        this.adminManager = adminManager;
        this.jwtTokenManager = jwtTokenManager;
        this.passwordEncoder = passwordEncoder;
    }


    public Boolean updatePassword(UpdatePasswordRequestDto dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        auth.get().setPassword(passwordEncoder.encode(dto.getPassword()));
        update(auth.get());
        return true;
    }
    public Boolean register(RegisterRequestDto dto) {
        if (authRepository.existsByEmail(dto.getEmail()))
            throw new AuthServiceException(EErrorType.EMAIL_ALREADY_TAKEN);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        String activationCode=Generator.randomActivationCode();
        auth.setActivationCode(activationCode);
        auth.setPassword(passwordEncoder.encode(dto.getPassword()));
        auth.setRole(ERole.valueOf(dto.getRole()));
        save(auth);
        adminManager.save(IAuthMapper.INSTANCE.toSaveRequestDto(auth));
        try {
            registerMailProducer.sendActivationCode(ActivateStatusModel.builder()
                    .activationCode(activationCode).email(dto.getEmail()).build());
        }catch (Exception exception){
            throw new AuthServiceException(EErrorType.INVALID_PARAMETER);
        }
        return true;
    }

    public Boolean reSendMail(String email){
        Optional<Auth>auth=authRepository.findByEmail(email);
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        String activationCode=Generator.randomActivationCode();
        auth.get().setActivationCode(activationCode);
        update(auth.get());
        registerMailProducer.sendActivationCode(ActivateStatusModel.builder()
                .activationCode(activationCode).email(email).build());
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
   public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<Auth> auth=findById(dto.getId());
        if (auth.isEmpty()){
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        }
        if (dto.getActivationCode().equals(auth.get().getActivationCode())){
            auth.get().setStatus(EStatus.ACTIVE);
            update(auth.get());
            return true;
        }else {
            throw new AuthServiceException(EErrorType.ACTIVATE_CODE_ERROR);
        }

    }

    public LoginResponse doLogin(LoginRequestDto dto) {
        Optional<Auth> optionalAuth= authRepository.findByEmail(dto.getEmail());
        if (optionalAuth.isEmpty() || !passwordEncoder.matches(dto.getPassword(), optionalAuth.get().getPassword()))
            throw new AuthServiceException(EErrorType.USER_NOT_FOUND);
        Auth auth = optionalAuth.get();
        Optional<String> token = jwtTokenManager.createToken(auth.getId(), auth.getRole());
        if (token.isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_PARAMETER);
        return LoginResponse.builder().id(auth.getId()).token(token.get()).build();
    }
}

