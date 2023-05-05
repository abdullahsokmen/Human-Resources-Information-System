package com.group.service;

import com.group.dto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.request.UpdateExpenditureRequestElasticDto;
import com.group.dto.response.ExpenditureResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.ElasticServiceException;
import com.group.mapper.IExpenditureMapper;
import com.group.repository.IExpenditureRepository;
import com.group.repository.entity.BaseEntity;
import com.group.repository.entity.DayOff;
import com.group.repository.entity.Expenditure;
import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EStatus;
import com.group.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class ExpenditureService extends ServiceManager<Expenditure,String> {

    private final IExpenditureRepository expenditureRepository;
    private final IExpenditureMapper expenditureDayOffMapper;

    public ExpenditureService(IExpenditureRepository expenditureRepository, IExpenditureMapper expenditureDayOffMapper) {
        super(expenditureRepository);
        this.expenditureRepository = expenditureRepository;
        this.expenditureDayOffMapper = expenditureDayOffMapper;
    }

    public void createExpenditure(CreateExpenditureRequestElasticDto dto) {
        save(expenditureDayOffMapper.toExpenditure(dto));
    }

    public void deleteExpenditure(Long id) {
        Optional<Expenditure> expenditure = expenditureRepository.findByExpenditureRequestId(id);
        if(expenditure.isEmpty())
            throw new ElasticServiceException(EErrorType.MUSTERI_BULUNAMADI);
        delete(expenditure.get());
    }

    public void updateExpenditure(UpdateExpenditureRequestElasticDto dto) {
        Optional<Expenditure> expenditure = expenditureRepository.findByExpenditureRequestId(dto.getExpenditureRequestId());
        if(expenditure.isEmpty())
            throw new ElasticServiceException(EErrorType.MUSTERI_BULUNAMADI);
        Expenditure toUpdate = expenditure.get();
        toUpdate.setAmount(dto.getAmount());
        toUpdate.setExpendDetails(dto.getExpendDetails());
        toUpdate.setCurrency(Currency.valueOf(dto.getCurrency()));
        toUpdate.setStatus(EStatus.valueOf(dto.getStatus()));
        toUpdate.setConfirmDate(dto.getConfirmDate());
        update(toUpdate);
    }

    public ExpenditureResponseDto getOneExpenditure(Long expenditureRequestId) {
        return IExpenditureMapper.INSTANCE.fromExpenditure(expenditureRepository.findByExpenditureRequestId(expenditureRequestId)
                .orElseThrow(()-> new ElasticServiceException(EErrorType.INVALID_PARAMETER)));
    }

    public Page<ExpenditureResponseDto> getAllExpenditure(Integer currentPage) {
        List<Expenditure> pending = new ArrayList<>();
        List<Expenditure> others = new ArrayList<>();
        List<Expenditure> allExpenditure = new ArrayList<>();
        Pageable pageable = PageRequest.of(currentPage,10);
        findAll().forEach(allExpenditure::add);
        List<Expenditure> sortedList = allExpenditure.stream().sorted(Comparator.comparing(BaseEntity::getCreateat)).toList();
        sortedList.forEach(x->{
            if (x.getStatus().equals(EStatus.PENDING)){
                pending.add(x);
            }else {
                others.add(x);
            }
        });
        List<ExpenditureResponseDto> results = Stream.of(pending,others).flatMap(Collection::stream).map(x->{
            ExpenditureResponseDto dto = IExpenditureMapper.INSTANCE.fromExpenditure(x);
            dto.setExpenditureType(x.getExpenditureType().name());
            dto.setCurrency(x.getCurrency().name());
            dto.setStatus(x.getStatus().name());
            return dto;
        }).toList();
        return new PageImpl<>(results,pageable,results.size());
    }

    public Page<ExpenditureResponseDto> getAllByPersonalId(Long personalId, Integer currentPage) {
        List<Expenditure> pending = new ArrayList<>();
        List<Expenditure> others = new ArrayList<>();
        List<Expenditure> allExpenditure = new ArrayList<>();
        Pageable pageable = PageRequest.of(currentPage,10);
        expenditureRepository.findAllByPersonalId(personalId).forEach(allExpenditure::add);
        List<Expenditure> sortedList = allExpenditure.stream().sorted(Comparator.comparing(BaseEntity::getCreateat)).toList();
        sortedList.forEach(x->{
            if (x.getStatus().equals(EStatus.PENDING)){
                pending.add(x);
            }else {
                others.add(x);
            }
        });
        List<ExpenditureResponseDto> results = Stream.of(pending,others).flatMap(Collection::stream).map(x->{
            ExpenditureResponseDto dto = IExpenditureMapper.INSTANCE.fromExpenditure(x);
            dto.setExpenditureType(x.getExpenditureType().name());
            dto.setCurrency(x.getCurrency().name());
            dto.setStatus(x.getStatus().name());
            return dto;
        }).toList();
        return new PageImpl<>(results,pageable,results.size());
    }
}
