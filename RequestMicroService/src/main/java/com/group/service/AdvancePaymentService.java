package com.group.service;

import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestDto;
import com.group.dto.Advancepaymentdto.request.UpdateAdvancePaymentRequestDto;
import com.group.dto.Advancepaymentdto.response.AdvancePaymentResponseDto;
import com.group.exception.EErrorType;
import com.group.exception.RequestException;
import com.group.mapper.IAdvancePaymentMapper;
import com.group.repository.IAdvancePaymenRepository;
import com.group.repository.entity.AdvancePayment;
import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EStatus;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AdvancePaymentService extends ServiceManager<AdvancePayment,Long> {
    private final IAdvancePaymenRepository advancePaymenRepository;


    public AdvancePaymentService(IAdvancePaymenRepository advancePaymenRepository) {
        super(advancePaymenRepository);
        this.advancePaymenRepository = advancePaymenRepository;
    }

    public Boolean requestAdvancePayment(CreateAdvancePaymentRequestDto dto) {
        AdvancePayment advancePayment= IAdvancePaymentMapper.INSTANCE.toAdvancePayment(dto);
        save(advancePayment);
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

    public AdvancePaymentResponseDto getDetails(Long id) {
        Optional<AdvancePayment>advancePayment=findById(id);
        if (advancePayment.isEmpty())
            throw new RequestException(EErrorType.INVALID_PARAMETER);
        AdvancePaymentResponseDto allDetails=IAdvancePaymentMapper.INSTANCE.fromAdvancePayment(advancePayment.get());
        return allDetails;
    }
}
