package com.group.mapper;

import com.group.dto.request.CreateAdvancePaymentRequestDto;
import com.group.dto.request.CreateAdvancePaymentRequestElasticDto;
import com.group.dto.request.UpdateAdvancePaymentRequestElasticDto;
import com.group.repository.entity.AdvancePayment;
import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-04T19:27:54+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAdvancePaymentMapperImpl implements IAdvancePaymentMapper {

    @Override
    public AdvancePayment toAdvancePayment(CreateAdvancePaymentRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        AdvancePayment.AdvancePaymentBuilder<?, ?> advancePayment = AdvancePayment.builder();

        if ( dto.getCurrency() != null ) {
            advancePayment.currency( Enum.valueOf( Currency.class, dto.getCurrency() ) );
        }
        advancePayment.amount( dto.getAmount() );
        advancePayment.advanceDetails( dto.getAdvanceDetails() );
        if ( dto.getAdvancePaymentType() != null ) {
            advancePayment.advancePaymentType( Enum.valueOf( EAdvancePaymentType.class, dto.getAdvancePaymentType() ) );
        }
        advancePayment.personalId( dto.getPersonalId() );

        return advancePayment.build();
    }

    @Override
    public CreateAdvancePaymentRequestElasticDto fromAdvancePaymentElastic(AdvancePayment advancePayment) {
        if ( advancePayment == null ) {
            return null;
        }

        CreateAdvancePaymentRequestElasticDto.CreateAdvancePaymentRequestElasticDtoBuilder createAdvancePaymentRequestElasticDto = CreateAdvancePaymentRequestElasticDto.builder();

        createAdvancePaymentRequestElasticDto.paymentRequestId( advancePayment.getId() );
        createAdvancePaymentRequestElasticDto.personalName( advancePayment.getPersonalName() );
        createAdvancePaymentRequestElasticDto.personalLastName( advancePayment.getPersonalLastName() );
        createAdvancePaymentRequestElasticDto.requestDate( advancePayment.getRequestDate() );
        createAdvancePaymentRequestElasticDto.confirmDate( advancePayment.getConfirmDate() );
        createAdvancePaymentRequestElasticDto.currency( advancePayment.getCurrency() );
        createAdvancePaymentRequestElasticDto.amount( advancePayment.getAmount() );
        createAdvancePaymentRequestElasticDto.advanceDetails( advancePayment.getAdvanceDetails() );
        createAdvancePaymentRequestElasticDto.advancePaymentType( advancePayment.getAdvancePaymentType() );
        createAdvancePaymentRequestElasticDto.status( advancePayment.getStatus() );
        createAdvancePaymentRequestElasticDto.personalId( advancePayment.getPersonalId() );

        return createAdvancePaymentRequestElasticDto.build();
    }

    @Override
    public UpdateAdvancePaymentRequestElasticDto fromAdvancePaymentElasticUpdate(AdvancePayment advancePayment) {
        if ( advancePayment == null ) {
            return null;
        }

        UpdateAdvancePaymentRequestElasticDto.UpdateAdvancePaymentRequestElasticDtoBuilder updateAdvancePaymentRequestElasticDto = UpdateAdvancePaymentRequestElasticDto.builder();

        updateAdvancePaymentRequestElasticDto.paymentRequestId( advancePayment.getId() );
        updateAdvancePaymentRequestElasticDto.advanceDetails( advancePayment.getAdvanceDetails() );
        if ( advancePayment.getCurrency() != null ) {
            updateAdvancePaymentRequestElasticDto.currency( advancePayment.getCurrency().name() );
        }
        updateAdvancePaymentRequestElasticDto.amount( advancePayment.getAmount() );
        if ( advancePayment.getStatus() != null ) {
            updateAdvancePaymentRequestElasticDto.status( advancePayment.getStatus().name() );
        }
        updateAdvancePaymentRequestElasticDto.confirmDate( advancePayment.getConfirmDate() );

        return updateAdvancePaymentRequestElasticDto.build();
    }
}
