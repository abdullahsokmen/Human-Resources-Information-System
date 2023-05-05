package com.group.mapper;

import com.group.dto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.response.AdvancePaymentResponseDto;
import com.group.repository.entity.AdvancePayment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-05T11:05:01+0300",
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

    @Override
    public AdvancePaymentResponseDto fromAdvancePayment(AdvancePayment advancePayment) {
        if ( advancePayment == null ) {
            return null;
        }

        AdvancePaymentResponseDto.AdvancePaymentResponseDtoBuilder advancePaymentResponseDto = AdvancePaymentResponseDto.builder();

        advancePaymentResponseDto.personalName( advancePayment.getPersonalName() );
        advancePaymentResponseDto.personalLastName( advancePayment.getPersonalLastName() );
        advancePaymentResponseDto.requestDate( advancePayment.getRequestDate() );
        advancePaymentResponseDto.confirmDate( advancePayment.getConfirmDate() );
        if ( advancePayment.getCurrency() != null ) {
            advancePaymentResponseDto.currency( advancePayment.getCurrency().name() );
        }
        advancePaymentResponseDto.amount( advancePayment.getAmount() );
        advancePaymentResponseDto.advanceDetails( advancePayment.getAdvanceDetails() );
        if ( advancePayment.getAdvancePaymentType() != null ) {
            advancePaymentResponseDto.advancePaymentType( advancePayment.getAdvancePaymentType().name() );
        }
        if ( advancePayment.getStatus() != null ) {
            advancePaymentResponseDto.status( advancePayment.getStatus().name() );
        }
        advancePaymentResponseDto.personalId( advancePayment.getPersonalId() );
        advancePaymentResponseDto.paymentRequestId( advancePayment.getPaymentRequestId() );
        advancePaymentResponseDto.createat( advancePayment.getCreateat() );

        return advancePaymentResponseDto.build();
    }
}
