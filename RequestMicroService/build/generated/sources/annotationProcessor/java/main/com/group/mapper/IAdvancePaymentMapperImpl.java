package com.group.mapper;

import com.group.dto.Advancepaymentdto.request.CreateAdvancePaymentRequestDto;
import com.group.dto.Advancepaymentdto.response.AdvancePaymentResponseDto;
import com.group.repository.entity.AdvancePayment;
import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-04T14:00:29+0300",
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

        return advancePayment.build();
    }

    @Override
    public AdvancePaymentResponseDto fromAdvancePayment(AdvancePayment advancePayment) {
        if ( advancePayment == null ) {
            return null;
        }

        AdvancePaymentResponseDto.AdvancePaymentResponseDtoBuilder advancePaymentResponseDto = AdvancePaymentResponseDto.builder();

        advancePaymentResponseDto.id( advancePayment.getId() );
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

        return advancePaymentResponseDto.build();
    }

    @Override
    public CreateAdvancePaymentRequestElasticDto fromAdvancePaymentElastic(AdvancePayment advancePayment) {
        if ( advancePayment == null ) {
            return null;
        }

        CreateAdvancePaymentRequestElasticDto.CreateAdvancePaymentRequestElasticDtoBuilder createAdvancePaymentRequestElasticDto = CreateAdvancePaymentRequestElasticDto.builder();

        if ( advancePayment.getCurrency() != null ) {
            createAdvancePaymentRequestElasticDto.currency( advancePayment.getCurrency().name() );
        }
        createAdvancePaymentRequestElasticDto.amount( advancePayment.getAmount() );
        createAdvancePaymentRequestElasticDto.advanceDetails( advancePayment.getAdvanceDetails() );
        if ( advancePayment.getAdvancePaymentType() != null ) {
            createAdvancePaymentRequestElasticDto.advancePaymentType( advancePayment.getAdvancePaymentType().name() );
        }
        createAdvancePaymentRequestElasticDto.personalId( advancePayment.getPersonalId() );
        createAdvancePaymentRequestElasticDto.personalName( advancePayment.getPersonalName() );
        createAdvancePaymentRequestElasticDto.personalLastName( advancePayment.getPersonalLastName() );

        return createAdvancePaymentRequestElasticDto.build();
    }

    @Override
    public UpdateAdvancePaymentRequestElasticDto fromAdvancePaymentElasticUpdate(AdvancePayment advancePayment) {
        if ( advancePayment == null ) {
            return null;
        }

        UpdateAdvancePaymentRequestElasticDto.UpdateAdvancePaymentRequestElasticDtoBuilder updateAdvancePaymentRequestElasticDto = UpdateAdvancePaymentRequestElasticDto.builder();

        updateAdvancePaymentRequestElasticDto.advanceDetails( advancePayment.getAdvanceDetails() );
        if ( advancePayment.getCurrency() != null ) {
            updateAdvancePaymentRequestElasticDto.currency( advancePayment.getCurrency().name() );
        }
        updateAdvancePaymentRequestElasticDto.amount( advancePayment.getAmount() );

        return updateAdvancePaymentRequestElasticDto.build();
    }
}
