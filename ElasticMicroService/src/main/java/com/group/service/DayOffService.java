package com.group.service;

import com.group.dto.request.DayOffSaveRequestElasticDto;
import com.group.dto.request.DayOffUpdateRequestElasticDto;
import com.group.dto.response.DayOffResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.ElasticServiceException;
import com.group.mapper.IDayOffMapper;
import com.group.repository.IDayOffRepository;
import com.group.repository.entity.BaseEntity;
import com.group.repository.entity.DayOff;
import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import com.group.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        toUpdate.setConfirmDate(dto.getConfirmDate());
        update(toUpdate);
    }

    public Boolean saveTest(DayOffSaveRequestElasticDto dto) {
        save(IDayOffMapper.INSTANCE.toDayOff(dto));
        return true;
    }

 /*   public DayOffResponseDto getOneDayOff(Long dayOffRequestId) {
        return IDayOffMapper.INSTANCE.fromDayOff(dayOffRepository.findByDayOffRequestId(dayOffRequestId)
                .orElseThrow(()-> new ElasticServiceException(EErrorType.INVALID_PARAMETER)));

                /*  List<DayOff> dayOffs = new ArrayList<>();
        findAll().forEach(x->{
            dayOffs.add(x);
        });
        Collections.sort(dayOffs, new Comparator<DayOff>() {
            @Override
            public int compare(DayOff o1, DayOff o2) {
                return o1.getRequestDate().compareTo(o2.getRequestDate());
            }
        });
        return dayOffs;*/
   // }

    public Page<DayOffResponseDto> getAllDayOff(Integer currentPage) {
        //deneme
     List<DayOff>pending=new ArrayList<>();
     List<DayOff>others=new ArrayList<>();
     List<DayOff> allDayOffs = new ArrayList<>();
     Pageable pageable = PageRequest.of(currentPage,4,null);
     findAll().forEach(x-> allDayOffs.add(x));
     allDayOffs.stream().sorted(Comparator.comparing(BaseEntity::getCreateat));
     allDayOffs.forEach(x->{
         if (x.getStatus().equals(EStatus.PENDING)){
             pending.add(x);
         }else {
             others.add(x);
         }
     });
     List<DayOffResponseDto> results= Stream.of(pending,others).flatMap(Collection::stream).map(x-> {
         DayOffResponseDto dto = IDayOffMapper.INSTANCE.toDayOffResponseDto(x);
         dto.setType(x.getType().name());
         dto.setStatus(x.getStatus().name());
         return dto;
     }).collect(Collectors.toList());
     return new PageImpl<>(results,pageable,results.size());
    }

}
