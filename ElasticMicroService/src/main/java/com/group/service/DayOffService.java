package com.group.service;

import com.group.dto.Dayoffdto.request.DayOffSaveRequestElasticDto;
import com.group.dto.Dayoffdto.request.DayOffUpdateRequestElasticDto;
import com.group.dto.Dayoffdto.response.DayOffResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.ElasticServiceException;
import com.group.mapper.IDayOffMapper;
import com.group.repository.IDayOffRepository;
import com.group.repository.entity.DayOff;
import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import com.group.utility.ServiceManager;
import org.elasticsearch.common.util.Comparators;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class DayOffService extends ServiceManager<DayOff,String> {
    private final IDayOffRepository dayOffRepository;
    private final IDayOffMapper dayOffMapper;

    public DayOffService(IDayOffRepository dayOffRepository, IDayOffMapper dayOffMapper) {
        super(dayOffRepository);
        this.dayOffRepository = dayOffRepository;
        this.dayOffMapper = dayOffMapper;
    }

    public void requestDayOff(DayOffSaveRequestElasticDto dto) {
        save(dayOffMapper.toDayOff(dto));
    }

    public void deleteDayOff(Long id) {
        Optional<DayOff> dayOff = dayOffRepository.findByDayOffRequestId(id);
        if(dayOff.isEmpty())
            throw new ElasticServiceException(EErrorType.MUSTERI_BULUNAMADI);
        delete(dayOff.get());
    }

    public void updateDayOff(DayOffUpdateRequestElasticDto dto) {
        Optional<DayOff> dayOff = dayOffRepository.findByDayOffRequestId(dto.getDayOffRequestId());
        if(dayOff.isEmpty())
            throw new ElasticServiceException(EErrorType.MUSTERI_BULUNAMADI);
        DayOff toUpdate = dayOff.get();
        toUpdate.setSpan(dto.getSpan());
        toUpdate.setType(EDayOffType.valueOf(dto.getType()));
        toUpdate.setStartingDate(dto.getStartingDate());
        toUpdate.setEndDate(dto.getEndDate());
        toUpdate.setStatus(EStatus.valueOf(dto.getStatus()));
        update(toUpdate);
    }

    public Boolean saveTest(DayOffSaveRequestElasticDto dto) {
        save(IDayOffMapper.INSTANCE.toDayOff(dto));
        return true;
    }

    public DayOffResponseDto getOneDayOff(Long dayOffRequestId) {
        return IDayOffMapper.INSTANCE.fromDayOff(dayOffRepository.findByDayOffRequestId(dayOffRequestId)
                .orElseThrow(()-> new ElasticServiceException(EErrorType.INVALID_PARAMETER)));
    }

    public List<DayOff> getAllDayOff() {
        List<DayOff> dayOffs = new ArrayList<>();
        findAll().forEach(x->{
            dayOffs.add(x);
        });
        Collections.sort(dayOffs, new Comparator<DayOff>() {
            @Override
            public int compare(DayOff o1, DayOff o2) {
                return o1.getRequestDate().compareTo(o2.getRequestDate());
            }
        });
        return dayOffs;
    }

}
