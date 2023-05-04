package com.group.mapper;

import com.group.dto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.repository.entity.AdvancePayment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-04T19:28:32+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAdvancePaymentMapperImpl implements IAdvancePaymentMapper {

    @Override
    public AdvancePayment toAdvancePayment(CreateAdvancePaymentRequestElasticDto dto) {
        if ( dto == null ) {
            return null;
        }

        AdvancePayment.AdvancePaymentBuilder<?, ?> advancePayment = AdvancePayment.builder();

        advancePayment.personalName( dto.getPersonalName() );
        advancePayment.personalLastName( dto.getPersonalLastName() );
        advancePayment.requestDate( dto.getRequestDate() );
        advancePayment.confirmDate( dto.getConfirmDate() );
        advancePayment.currency( dto.getCurrency() );
        advancePayment.amount( dto.getAmount() );
        advancePayment.advanceDetails( dto.getAdvanceDetails() );
        advancePayment.advancePaymentType( dto.getAdvancePaymentType() );
        advancePayment.status( dto.getStatus() );
        advancePayment.personalId( dto.getPersonalId() );
        advancePayment.paymentRequestId( dto.getPaymentRequestId() );

        return advancePayment.build();
    }
}
