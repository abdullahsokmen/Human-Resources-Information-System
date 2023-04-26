package com.group.service;

import com.group.dto.request.PersonalSaveRequestDto;
import com.group.exception.EErrorType;
import com.group.exception.PersonalException;
import com.group.manager.ICompanyManager;
import com.group.repository.IPersonalRepository;
import com.group.repository.entity.Personal;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class PersonalService extends ServiceManager<Personal,Long> {
    private final IPersonalRepository personalRepository;
    private final ICompanyManager companyManager;

    public PersonalService(IPersonalRepository personalRepository, ICompanyManager companyManager) {
        super(personalRepository);
        this.personalRepository = personalRepository;
        this.companyManager = companyManager;
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
}
