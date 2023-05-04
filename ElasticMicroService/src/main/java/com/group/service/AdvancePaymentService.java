package com.group.service;

import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.Advancepaymentdto.request.UpdateAdvancePaymentRequestElasticDto;
import com.group.exception.EErrorType;
import com.group.exception.ElasticServiceException;
import com.group.mapper.IAdvancePaymentMapper;
import com.group.repository.IAdvancePaymentRepository;
import com.group.repository.entity.AdvancePayment;
import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import com.group.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        save(AdvancePayment.builder()
                .advanceDetails(dto.getAdvanceDetails())
                .advancePaymentType(EAdvancePaymentType.valueOf(dto.getAdvancePaymentType()))
                .personalName(dto.getPersonalName())
                .personalLastName(dto.getPersonalLastName())
                .paymentRequestId(dto.getPaymentRequestId())
                .build());
    }

    public void updateAdvancePayment(UpdateAdvancePaymentRequestElasticDto dto) {
        Optional<AdvancePayment> advancePayment = advancePaymentRepository.findByPaymentRequestId(dto.getPaymentRequestId());
        if(advancePayment.isEmpty())
            throw new ElasticServiceException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
        AdvancePayment toUpdate = advancePayment.get();
        toUpdate.setAmount(dto.getAmount());
        toUpdate.setAdvanceDetails(dto.getAdvanceDetails());
        toUpdate.setCurrency(Currency.valueOf(dto.getCurrency()));
        update(toUpdate);
    }

    public void deletePayment(Long id) {
        Optional<AdvancePayment> advancePayment = advancePaymentRepository.findByPaymentRequestId(id);
        if(advancePayment.isEmpty())
            throw new ElasticServiceException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
        delete(advancePayment.get());
    }
}
