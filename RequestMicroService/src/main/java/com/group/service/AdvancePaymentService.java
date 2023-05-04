package com.group.service;

import com.group.dto.request.CreateAdvancePaymentRequestDto;
import com.group.dto.request.UpdateAdvancePaymentRequestDto;
import com.group.dto.response.PersonalInfoResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.RequestException;
import com.group.manager.IAdvancePaymentManager;
import com.group.manager.IPersonalManager;
import com.group.mapper.IAdvancePaymentMapper;
import com.group.repository.IAdvancePaymenRepository;
import com.group.repository.entity.AdvancePayment;
import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import com.group.repository.entity.enums.EStatus;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdvancePaymentService extends ServiceManager<AdvancePayment,Long> {
    private final IAdvancePaymenRepository advancePaymenRepository;
    private final IPersonalManager personalManager;
    private final IAdvancePaymentManager advancePaymentManager;
    private final IAdvancePaymentMapper advancePaymentMapper;


    public AdvancePaymentService(IAdvancePaymenRepository advancePaymenRepository, IPersonalManager personalManager, IAdvancePaymentManager advancePaymentManager, IAdvancePaymentMapper advancePaymentMapper) {
        super(advancePaymenRepository);
        this.advancePaymenRepository = advancePaymenRepository;
        this.personalManager = personalManager;
        this.advancePaymentManager = advancePaymentManager;

        this.advancePaymentMapper = advancePaymentMapper;
    }

    public Boolean requestAdvancePayment(CreateAdvancePaymentRequestDto dto) {
        PersonalInfoResponseDto personalDto=personalManager.getPersonalInfo(dto.getPersonalId()).getBody();
        if (Objects.isNull(personalDto))
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        if (dto.getAmount()>=personalDto.getSalary()*3)
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        AdvancePayment advancePayment= IAdvancePaymentMapper.INSTANCE.toAdvancePayment(dto);
        advancePayment.setPersonalName(personalDto.getName());
        advancePayment.setPersonalLastName(personalDto.getLastname());
        advancePayment.setPersonalId(dto.getPersonalId());
        advancePayment.setCurrency(Currency.valueOf(dto.getCurrency()));
        advancePayment.setAdvancePaymentType(EAdvancePaymentType.valueOf(dto.getAdvancePaymentType()));
        save(advancePayment);
        advancePaymentManager.requestAdvancePayment(advancePaymentMapper.fromAdvancePaymentElastic(advancePayment));
        return true;
    }

    public Boolean updateAdvancePayment(UpdateAdvancePaymentRequestDto dto) {
        Optional<AdvancePayment>advancePayment=findById(dto.getId());
        if (advancePayment.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        AdvancePayment toUpdate=advancePayment.get();
        toUpdate.setAdvanceDetails(dto.getAdvanceDetails());
        toUpdate.setAmount(dto.getAmount());
        toUpdate.setCurrency(Currency.valueOf(dto.getCurrency()));
        update(toUpdate);
        advancePaymentManager.updateAdvancePayment(advancePaymentMapper.fromAdvancePaymentElasticUpdate(toUpdate));
        return true;
    }

    public Boolean confirmAdvancePayment(Long id) {
        Optional<AdvancePayment>advancePayment=findById(id);
        if (advancePayment.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        advancePayment.get().setStatus(EStatus.CONFIRMED);
        advancePayment.get().setConfirmDate(new Date());
        update(advancePayment.get());
        return true;
    }

    public Boolean deletePayment(Long id) {
        Optional<AdvancePayment>advancePayment=findById(id);
        if (advancePayment.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        delete(advancePayment.get());
        advancePaymentManager.deleteAdvancePayment(id);
        return true;
    }

    public Boolean declineAdvancePayment(Long id) {
        Optional<AdvancePayment>advancePayment=findById(id);
        if (advancePayment.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        advancePayment.get().setStatus(EStatus.DECLINED);
        update(advancePayment.get());
        return true;
    }

}
