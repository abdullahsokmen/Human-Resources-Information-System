package com.group.service;


import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.PersonalException;
import com.group.mapper.IPersonalMapper;

import com.group.dto.request.PersonalSaveRequestDto;

import com.group.repository.IPersonalRepository;
import com.group.repository.entity.Personal;
import com.group.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalService extends ServiceManager<Personal,Long> {
    private final IPersonalRepository personalRepository;

    public PersonalService(IPersonalRepository personalRepository) {
        super(personalRepository);
        this.personalRepository = personalRepository;
    }

    public PersonalMinorDetailsResponseDto getMinorDetails(Long id) {
        Optional<Personal> personal = personalRepository.findById(id);
        if(personal.isEmpty())
            throw new PersonalException(EErrorType.PERSONAL_NOT_FOUND);
        return IPersonalMapper.INSTANCE.fromPersonal(personal.get());
    }

    public Boolean createPersonal(PersonalSaveRequestDto dto) {

    }
}
