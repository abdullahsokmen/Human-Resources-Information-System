package com.group.service;

import com.group.dto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.request.UpdateAdvancePaymentRequestElasticDto;
import com.group.dto.response.AdvancePaymentResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.ElasticServiceException;
import com.group.mapper.IAdvancePaymentMapper;
import com.group.repository.IAdvancePaymentRepository;
import com.group.repository.entity.AdvancePayment;
import com.group.repository.entity.BaseEntity;
import com.group.repository.entity.DayOff;
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
public class AdvancePaymentService extends ServiceManager<AdvancePayment,String> {

    private final IAdvancePaymentRepository advancePaymentRepository;
    private final IAdvancePaymentMapper mapper;


    public AdvancePaymentService(IAdvancePaymentRepository advancePaymentRepository, IAdvancePaymentMapper mapper) {
        super(advancePaymentRepository);
        this.advancePaymentRepository = advancePaymentRepository;
        this.mapper = mapper;
    }

    public void requestAdvancePayment(CreateAdvancePaymentRequestElasticDto dto) {
        save(mapper.toAdvancePayment(dto));
    }

    public void updateAdvancePayment(UpdateAdvancePaymentRequestElasticDto dto) {
        Optional<AdvancePayment> advancePayment = advancePaymentRepository.findByPaymentRequestId(dto.getPaymentRequestId());
        if(advancePayment.isEmpty())
            throw new ElasticServiceException(EErrorType.METHOD_MIS_MATCH_ERROR);
        AdvancePayment toUpdate = advancePayment.get();
        toUpdate.setAmount(dto.getAmount());
        toUpdate.setAdvanceDetails(dto.getAdvanceDetails());
        toUpdate.setCurrency(Currency.valueOf(dto.getCurrency()));
        toUpdate.setStatus(EStatus.valueOf(dto.getStatus()));
        toUpdate.setConfirmDate(dto.getConfirmDate());
        update(toUpdate);
    }

    public void deletePayment(Long id) {
        Optional<AdvancePayment> advancePayment = advancePaymentRepository.findByPaymentRequestId(id);
        if(advancePayment.isEmpty())
            throw new ElasticServiceException(EErrorType.PERSONAL_NOT_FOUND);
        delete(advancePayment.get());
    }

    public AdvancePaymentResponseDto getOneAdvancePayment(Long paymentRequestId) {
        return mapper.fromAdvancePayment(advancePaymentRepository.findByPaymentRequestId(paymentRequestId)
                .orElseThrow(()-> new ElasticServiceException(EErrorType.INVALID_PARAMETER)));
    }

    public Page<AdvancePaymentResponseDto> getAllAdvancePayment(Integer currentPage) {
        List<AdvancePayment> pending = new ArrayList<>();
        List<AdvancePayment> others = new ArrayList<>();
        List<AdvancePayment> allAdvancePayments = new ArrayList<>();
        Pageable pageable = PageRequest.of(currentPage,5);
        findAll().forEach(allAdvancePayments::add);
        List<AdvancePayment> sortedList = allAdvancePayments.stream().sorted(Comparator.comparing(BaseEntity::getCreateat)).toList();
        sortedList.forEach(x-> {
            if (x.getStatus().equals(EStatus.PENDING)){
                pending.add(x);
            }else {
                others.add(x);
            }
        });
        List<AdvancePaymentResponseDto> results = Stream.of(pending,others).flatMap(Collection::stream).map(x->{
            AdvancePaymentResponseDto dto = mapper.fromAdvancePayment(x);
            dto.setStatus(x.getStatus().name());
            dto.setAdvancePaymentType(x.getAdvancePaymentType().name());
            dto.setCurrency(x.getCurrency().name());
            return dto;
        }).toList();
        return new PageImpl<>(results,pageable,results.size());
    }

    public Page<AdvancePaymentResponseDto> getAllByPersonalId(Long personalId, Integer currentPage) {
        List<AdvancePayment> pending = new ArrayList<>();
        List<AdvancePayment> others = new ArrayList<>();
        List<AdvancePayment> allAdvancePayments = new ArrayList<>();
        Pageable pageable = PageRequest.of(currentPage,10);
        advancePaymentRepository.findAllByPersonalId(personalId).forEach(allAdvancePayments::add);
        List<AdvancePayment> sortedList = allAdvancePayments.stream().sorted(Comparator.comparing(BaseEntity::getCreateat)).toList();
        sortedList.forEach(x-> {
            if (x.getStatus().equals(EStatus.PENDING)){
                pending.add(x);
            }else {
                others.add(x);
            }
        });
        List<AdvancePaymentResponseDto> results = Stream.of(pending,others).flatMap(Collection::stream).map(x->{
            AdvancePaymentResponseDto dto = mapper.fromAdvancePayment(x);
            dto.setStatus(x.getStatus().name());
            dto.setAdvancePaymentType(x.getAdvancePaymentType().name());
            dto.setCurrency(x.getCurrency().name());
            return dto;
        }).toList();
        return new PageImpl<>(results,pageable,results.size());
    }
}
