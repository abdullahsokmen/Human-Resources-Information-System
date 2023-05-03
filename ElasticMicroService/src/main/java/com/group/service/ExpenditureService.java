package com.group.service;

import com.group.dto.Expendituredto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.Expendituredto.request.UpdateExpenditureRequestElasticDto;
import com.group.exception.EErrorType;
import com.group.exception.ElasticServiceException;
import com.group.mapper.IExpenditureDayOffMapper;
import com.group.repository.IExpenditureRepository;
import com.group.repository.entity.Expenditure;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenditureService extends ServiceManager<Expenditure,String> {

    private final IExpenditureRepository expenditureRepository;
    private final IExpenditureDayOffMapper expenditureDayOffMapper;

    public ExpenditureService(IExpenditureRepository expenditureRepository, IExpenditureDayOffMapper expenditureDayOffMapper) {
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
        Expenditure toUpdate = expenditureDayOffMapper.toExpenditure(dto);
        toUpdate.setPersonalName(expenditure.get().getPersonalName());
        toUpdate.setPersonalLastName(expenditure.get().getPersonalLastName());
        toUpdate.setId(expenditure.get().getId());
        toUpdate.setAmount(expenditure.get().getAmount());
        toUpdate.setExpendDetails(expenditure.get().getExpendDetails());
        toUpdate.setCurrency(expenditure.get().getCurrency());
        toUpdate.setRequestDate(expenditure.get().getRequestDate());
        update(toUpdate);
    }
}