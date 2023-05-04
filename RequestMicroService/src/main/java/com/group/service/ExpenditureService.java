package com.group.service;

import com.group.dto.request.CreateExpenditureRequestDto;
import com.group.dto.request.UpdateExpenditureRequestDto;
import com.group.dto.response.PersonalInfoResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.RequestException;
import com.group.manager.IExpenditureManager;
import com.group.manager.IPersonalManager;
import com.group.mapper.IExpenditureDayOffMapper;
import com.group.repository.IExpenditureRepository;
import com.group.repository.entity.Expenditure;
import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EStatus;
import com.group.repository.entity.enums.ExpenditureType;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenditureService extends ServiceManager<Expenditure,Long> {
   private final IExpenditureRepository expenditureRepository;
   private final IPersonalManager personalManager;
   private final IExpenditureDayOffMapper expenditureDayOffMapper;
   private final IExpenditureManager expenditureManager;


    public ExpenditureService(IExpenditureRepository expenditureRepository, IPersonalManager personalManager, IExpenditureDayOffMapper expenditureDayOffMapper, IExpenditureManager expenditureManager) {
        super(expenditureRepository);
        this.expenditureRepository = expenditureRepository;
        this.personalManager = personalManager;
        this.expenditureDayOffMapper = expenditureDayOffMapper;

        this.expenditureManager = expenditureManager;
    }

    public Boolean createExpenditure(CreateExpenditureRequestDto dto) {
        PersonalInfoResponseDto personalDto=personalManager.getPersonalInfo(dto.getPersonalId()).getBody();
        if (Objects.isNull(personalDto))
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        Expenditure expenditure= IExpenditureDayOffMapper.INSTANCE.toExpenditure(dto);
        expenditure.setPersonalName(personalDto.getName());
        expenditure.setPersonalLastName(personalDto.getLastname());
        expenditure.setCurrency(Currency.valueOf(dto.getCurrency()));
        save(expenditure);
        expenditureManager.saveExpenditure(expenditureDayOffMapper.fromExpenditureElastic(expenditure));
        return true;
    }

    public Boolean deleteExpenditure(Long id) {
        Optional<Expenditure>expenditure=findById(id);
        if (expenditure.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        delete(expenditure.get());
        expenditureManager.deleteExpenditure(id);
        return true;
    }

    public Boolean updateExpenditure(UpdateExpenditureRequestDto dto) {
        Optional<Expenditure>expenditure=findById(dto.getId());
        if (expenditure.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        Expenditure toUpdate=expenditure.get();
        toUpdate.setExpenditureType(ExpenditureType.valueOf(dto.getExpenditureType()));
        toUpdate.setAmount(dto.getAmount());
        toUpdate.setCurrency(Currency.valueOf(dto.getCurrency()));
        toUpdate.setExpendDetails(dto.getExpendDetails());
        update(toUpdate);
        expenditureManager.updateExpenditure(expenditureDayOffMapper.fromExpenditureElasticUpdate(toUpdate));
        return true;
    }

    public Boolean confirmExpenditure(Long id) {
        Optional<Expenditure>expenditure=findById(id);
        if (expenditure.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        expenditure.get().setStatus(EStatus.CONFIRMED);
        expenditure.get().setConfirmDate(new Date());
        update(expenditure.get());
        return true;
    }

    public Boolean declineExpenditure(Long id) {
        Optional<Expenditure>expenditure=findById(id);
        if (expenditure.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        expenditure.get().setStatus(EStatus.DECLINED);
        update(expenditure.get());
        return true;
    }

}
