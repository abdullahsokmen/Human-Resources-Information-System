package com.group.service;


import com.group.dto.request.*;
import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.PersonalException;
import com.group.manager.IAuthManager;
import com.group.mapper.IAddressMapper;
import com.group.mapper.IPersonalMapper;

import com.group.manager.ICompanyManager;


import com.group.rabbitmq.model.PersonalPasswordSenderModel;
import com.group.rabbitmq.producer.PersonalPasswordProducer;
import com.group.repository.IPersonalRepository;
import com.group.repository.entity.Address;
import com.group.repository.entity.EStatus;
import com.group.repository.entity.Personal;
import com.group.utility.Generator;
import com.group.utility.JwtTokenManager;
import com.group.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService extends ServiceManager<Personal,Long> {
    private final IPersonalRepository personalRepository;
    private final PasswordEncoder passwordEncoder;
    private final ICompanyManager companyManager;
    private final PersonalPasswordProducer personalPasswordProducer;
    private final IAuthManager authManager;
    private final JwtTokenManager jwtTokenManager;

    public PersonalService(IPersonalRepository personalRepository, PasswordEncoder passwordEncoder,
                           ICompanyManager companyManager, PersonalPasswordProducer personalPasswordProducer, IAuthManager authManager, JwtTokenManager jwtTokenManager) {
        super(personalRepository);
        this.personalRepository = personalRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyManager = companyManager;
        this.personalPasswordProducer = personalPasswordProducer;
        this.authManager = authManager;
        this.jwtTokenManager = jwtTokenManager;
    }


    public PersonalMinorDetailsResponseDto getMinorDetails(Long id) {
        Optional<Personal> personal = personalRepository.findById(id);
        if(personal.isEmpty())
            throw new PersonalException(EErrorType.PERSONAL_NOT_FOUND);
        return IPersonalMapper.INSTANCE.fromPersonal(personal.get());

    }

    public Boolean createPersonal(PersonalSaveRequestDto dto) {
        if (personalRepository.existsByEmail(dto.getEmail()))
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        if (personalRepository.existsByIdentity(dto.getIdentity()))
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        if (personalRepository.existsByPhone(dto.getPhone()))
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        if (!companyManager.exitsById(dto.getCompanyId()).getBody())
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        Personal personal = IPersonalMapper.INSTANCE.toPersonal(dto);
        personal.setAddress(IAddressMapper.INSTANCE.toAddress(dto.getAddress()));
        String password = Generator.randomPassword();
        personal.setPassword(passwordEncoder.encode(password));
        RegisterRequestDto register = IPersonalMapper.INSTANCE.toRegisterRequestDto(personal);
        register.setUserRole("PERSONAL");
        register.setPassword(password);
        Long authId = authManager.register(register).getBody();
        personal.setAuthId(authId);
        save(personal);
        companyManager.addPersonal(personal.getCompanyId());
        personalPasswordProducer.sendPersonalPassword(PersonalPasswordSenderModel.builder()
                .email(personal.getEmail()).password(password).build());
        return true;
    }


    public Boolean updatePersonal(PersonalUpdateRequestDto dto) {
        Optional<Personal> personal = findById(dto.getId());
        if (personal.isEmpty())
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        if (personalRepository.existsByEmail(dto.getEmail()))
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        if (personalRepository.existsByIdentity(dto.getIdentity()))
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        if (personalRepository.existsByPhone(dto.getPhone()))
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        Personal toUpdate = personal.get();
        Address newAddress = IAddressMapper.INSTANCE.toAddress(dto.getAddress());
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

    public Boolean deActivateById(Long id) {
        Optional<Personal> personal = findById(id);
        if (personal.isEmpty())
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        personal.get().setStatus(EStatus.NOT_ACTIVE);
        update(personal.get());
        authManager.deactivateById(personal.get().getAuthId());
        return true;
    }

    public Boolean deletePersonalById(Long id) {
        Optional<Personal> personal = findById(id);
        if (personal.isEmpty())
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        personal.get().setStatus(EStatus.DELETED);
        update(personal.get());
        companyManager.deletePersonal(personal.get().getCompanyId());
        authManager.deactivateById(personal.get().getAuthId());
        return true;
    }

    public Boolean hardDeleteById(Long id) {
        Optional<Personal> personal = findById(id);
        if (personal.isEmpty())
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        deleteById(id);
        companyManager.deletePersonal(personal.get().getCompanyId());
        authManager.deleteByAuthId(personal.get().getAuthId());
        return true;
    }

    public List<PersonalMinorDetailsResponseDto> getAllPersonals() {
        return findAll().stream().map(x -> IPersonalMapper.INSTANCE.fromPersonal(x)).toList();

    }

    public Boolean updatePassword(UpdatePersonalPasswordRequestDto dto) {
        Optional<Personal> personal = findById(dto.getId());
        if (personal.isEmpty())
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        personal.get().setPassword(passwordEncoder.encode(dto.getPassword()));
        update(personal.get());
        authManager.updatePassword(UpdatePasswordRequestDto.builder().id(personal.get().getAuthId()).password(dto.getPassword()).build());
        return true;
    }
}