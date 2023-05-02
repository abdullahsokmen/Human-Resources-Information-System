package com.group.service;

import com.group.dto.Dayoffdto.DayOffSaveRequestDto;
import com.group.dto.Dayoffdto.DayOffUpdateRequestDto;
import com.group.exception.EErrorType;
import com.group.exception.GlobalExceptionHandler;
import com.group.exception.RequestServiceException;
import com.group.mapper.IDayOffMapper;
import com.group.repository.IDayOffRepository;
import com.group.repository.entity.DayOff;
import com.group.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DayOffService extends ServiceManager<DayOff,Long> {
    private final IDayOffRepository dayOffRepository;

    public DayOffService(IDayOffRepository dayOffRepository) {
        super(dayOffRepository);
        this.dayOffRepository = dayOffRepository;
    }

    public ResponseEntity<Boolean> requestDayOff(DayOffSaveRequestDto dto) {
        DayOff dayOff = IDayOffMapper.INSTANCE.toDayOff(dto);
        save(dayOff);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> deleteDayOff(Long id) {
        Optional<DayOff> dayOff = findById(id);
        delete(dayOff.get());
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> updateDayOff(DayOffUpdateRequestDto dto) {
        Optional<DayOff> dayOff = findById(dto.getId());
        if(dayOffRepository.existsById(dto.getId()))
            throw new RequestServiceException(EErrorType.UNEXPECTED_ERROR);
        DayOff toUpdate = dayOff.get();
        toUpdate.setStartingDate(dto.getStartingDate());
        toUpdate.setEndDate(dto.getEndDate());
        toUpdate.setSpan(dto.getSpan());
        update(toUpdate);
        return ResponseEntity.ok(true);
    }
}
