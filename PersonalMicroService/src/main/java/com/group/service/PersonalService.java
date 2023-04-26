package com.group.service;


import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.PersonalException;
import com.group.mapper.IPersonalMapper;

import com.group.dto.request.PersonalSaveRequestDto;

import com.group.manager.ICompanyManager;



import com.group.repository.IPersonalRepository;
import com.group.repository.entity.Personal;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService extends ServiceManager<Personal,Long> {
    private final IPersonalRepository personalRepository;

    private final ICompanyManager companyManager;


    public PersonalService(IPersonalRepository personalRepository, ICompanyManager companyManager) {
        super(personalRepository);
        this.personalRepository = personalRepository;
        this.companyManager = companyManager;
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
        if (companyManager.exitsById(dto.getCompanyId()).getBody())
            throw new PersonalException(EErrorType.INVALID_PARAMETER);
        return true;
    }

    public List<PersonalMinorDetailsResponseDto> getPersonalList() {
        return findAll().stream().map(x ->
                IPersonalMapper.INSTANCE.fromPersonal(x)).toList();
    }
}
