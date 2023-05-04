package com.group.service;

import com.group.dto.request.DayOffSaveRequestDto;
import com.group.dto.request.DayOffUpdateRequestDto;
import com.group.dto.response.PersonalInfoResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.RequestException;
import com.group.manager.IDayOffManager;
import com.group.manager.IPersonalManager;
import com.group.mapper.IDayOffMapper;
import com.group.repository.IDayOffRepository;
import com.group.repository.entity.DayOff;
import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DayOffService extends ServiceManager<DayOff,Long> {
    private final IDayOffRepository dayOffRepository;
    private final IPersonalManager personalManager;

    private final IDayOffManager dayOffManager;
    private final IDayOffMapper dayOffMapper;

    public DayOffService(IDayOffRepository dayOffRepository, IPersonalManager personalManager, IDayOffManager dayOffManager, IDayOffMapper dayOffMapper) {
        super(dayOffRepository);
        this.dayOffRepository = dayOffRepository;
        this.personalManager = personalManager;
        this.dayOffManager = dayOffManager;


        this.dayOffMapper = dayOffMapper;
    }

    public Boolean requestDayOff(DayOffSaveRequestDto dto) {
        PersonalInfoResponseDto personalDto=personalManager.getPersonalInfo(dto.getPersonalId()).getBody();
        System.out.println(personalDto);
        DayOff dayOff = IDayOffMapper.INSTANCE.toDayOff(dto);
        dayOff.setPersonalName(personalDto.getName());
        dayOff.setPersonalLastName(personalDto.getLastname());
        dayOff.setType(EDayOffType.valueOf(dto.getType()));
        save(dayOff);
        dayOffManager.requestDayOff(dayOffMapper.fromDayOffElastic(dayOff));
        return true;
    }

    public Boolean deleteDayOff(Long id) {
        Optional<DayOff> dayOff = findById(id);
        delete(dayOff.get());
        dayOffManager.deleteDayOff(id);
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
        dayOffManager.updateDayOff(dayOffMapper.fromDayOffElasticUpdate(dayOff.get()));
        return true;
    }

    public Boolean acceptDayOffRequest(Long id) {
        Optional<DayOff> dayOff = dayOffRepository.findById(id);
        dayOff.get().setStatus(EStatus.CONFIRMED);
        dayOff.get().setConfirmDate(new Date());
        update(dayOff.get());
        dayOffManager.updateDayOff(dayOffMapper.fromDayOffElasticUpdate(dayOff.get()));
        return true;
    }
    public Boolean declineDayOffRequest(Long id) {
        Optional<DayOff> dayOff = dayOffRepository.findById(id);
        dayOff.get().setStatus(EStatus.DECLINED);
        update(dayOff.get());
        dayOffManager.updateDayOff(dayOffMapper.fromDayOffElasticUpdate(dayOff.get()));
        return true;
    }


}
