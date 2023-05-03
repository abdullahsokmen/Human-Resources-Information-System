package com.group.service;

import com.group.dto.Dayoffdto.request.DayOffSaveRequestDto;
import com.group.dto.Dayoffdto.request.DayOffUpdateRequestDto;
import com.group.dto.Dayoffdto.response.DayOffResponseDto;
import com.group.dto.PersonalInfoResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.RequestException;
import com.group.manager.IPersonalManager;
import com.group.mapper.IDayOffMapper;
import com.group.repository.IDayOffRepository;
import com.group.repository.entity.DayOff;
import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DayOffService extends ServiceManager<DayOff,Long> {
    private final IDayOffRepository dayOffRepository;
    private final IPersonalManager personalManager;

    public DayOffService(IDayOffRepository dayOffRepository, IPersonalManager personalManager) {
        super(dayOffRepository);
        this.dayOffRepository = dayOffRepository;
        this.personalManager = personalManager;
    }

    public Boolean requestDayOff(DayOffSaveRequestDto dto) {
        PersonalInfoResponseDto personalDto=personalManager.getPersonalInfo(dto.getPersonalId()).getBody();
        if (Objects.isNull(personalDto))
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        DayOff dayOff = IDayOffMapper.INSTANCE.toDayOff(dto);
        dayOff.setPersonalName(personalDto.getName());
        dayOff.setPersonalLastName(personalDto.getLastname());
        dayOff.setType(EDayOffType.valueOf(dto.getType()));
        save(dayOff);
        return true;
    }

    public Boolean deleteDayOff(Long id) {
        Optional<DayOff> dayOff = findById(id);
        delete(dayOff.get());
        return true;
    }

    public Boolean updateDayOff(DayOffUpdateRequestDto dto) {
        Optional<DayOff> dayOff = findById(dto.getId());
        if(!dayOffRepository.existsById(dto.getId()))
            throw new RequestException(EErrorType.UNEXPECTED_ERROR);
        DayOff toUpdate = dayOff.get();
        toUpdate.setStartingDate(dto.getStartingDate());
        toUpdate.setEndDate(dto.getEndDate());
        toUpdate.setSpan(dto.getSpan());
        update(toUpdate);
        return true;
    }

    public DayOffResponseDto getDayOffDetails(Long id) {
        Optional<DayOff> dayOff = dayOffRepository.findById(id);
        if(dayOff.isEmpty())
            throw new RequestException(EErrorType.COMPANY_ADMIN_NOT_EXIST);
        PersonalInfoResponseDto personalDto=personalManager.getPersonalInfo(dayOff.get().getPersonalId()).getBody();
        DayOffResponseDto details = IDayOffMapper.INSTANCE.fromDayOff(dayOff.get());
        details.setType(dayOff.get().getType().name());
        details.setStatus(dayOff.get().getStatus().name());
        details.setName(personalDto.getName());
        details.setLastname(personalDto.getLastname());
        return details;
    }

    public Boolean acceptDayOffRequest(Long id) {
        Optional<DayOff> dayOff = dayOffRepository.findById(id);
        dayOff.get().setStatus(EStatus.CONFIRMED);
        dayOff.get().setConfirmDate(new Date());
        save(dayOff.get());
        return true;
    }
    public Boolean declineDayOffRequest(Long id) {
        Optional<DayOff> dayOff = dayOffRepository.findById(id);
        dayOff.get().setStatus(EStatus.DECLINED);
        save(dayOff.get());
        return true;
    }


}
