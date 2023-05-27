package com.group.mapper;

import com.group.dto.request.CreateExpenditureRequestDto;
import com.group.dto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.request.UpdateExpenditureRequestElasticDto;
import com.group.repository.entity.Expenditure;
import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.ExpenditureType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-27T13:12:27+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IExpenditureDayOffMapperImpl implements IExpenditureDayOffMapper {

    @Override
    public Expenditure toExpenditure(CreateExpenditureRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Expenditure.ExpenditureBuilder<?, ?> expenditure = Expenditure.builder();

        if ( dto.getExpenditureType() != null ) {
            expenditure.expenditureType( Enum.valueOf( ExpenditureType.class, dto.getExpenditureType() ) );
        }
        expenditure.amount( dto.getAmount() );
        if ( dto.getCurrency() != null ) {
            expenditure.currency( Enum.valueOf( Currency.class, dto.getCurrency() ) );
        }
        expenditure.expendDetails( dto.getExpendDetails() );
        expenditure.personalId( dto.getPersonalId() );

        return expenditure.build();
    }

    @Override
    public CreateExpenditureRequestElasticDto fromExpenditureElastic(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }

        CreateExpenditureRequestElasticDto.CreateExpenditureRequestElasticDtoBuilder createExpenditureRequestElasticDto = CreateExpenditureRequestElasticDto.builder();

        createExpenditureRequestElasticDto.expenditureRequestId( expenditure.getId() );
        createExpenditureRequestElasticDto.personalName( expenditure.getPersonalName() );
        createExpenditureRequestElasticDto.personalLastName( expenditure.getPersonalLastName() );
        createExpenditureRequestElasticDto.status( expenditure.getStatus() );
        createExpenditureRequestElasticDto.expenditureType( expenditure.getExpenditureType() );
        createExpenditureRequestElasticDto.amount( expenditure.getAmount() );
        createExpenditureRequestElasticDto.requestDate( expenditure.getRequestDate() );
        createExpenditureRequestElasticDto.confirmDate( expenditure.getConfirmDate() );
        createExpenditureRequestElasticDto.currency( expenditure.getCurrency() );
        createExpenditureRequestElasticDto.expendDetails( expenditure.getExpendDetails() );
        createExpenditureRequestElasticDto.personalId( expenditure.getPersonalId() );

        return createExpenditureRequestElasticDto.build();
    }

    @Override
    public UpdateExpenditureRequestElasticDto fromExpenditureElasticUpdate(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }

        UpdateExpenditureRequestElasticDto.UpdateExpenditureRequestElasticDtoBuilder updateExpenditureRequestElasticDto = UpdateExpenditureRequestElasticDto.builder();

        updateExpenditureRequestElasticDto.expenditureRequestId( expenditure.getId() );
        if ( expenditure.getExpenditureType() != null ) {
            updateExpenditureRequestElasticDto.expenditureType( expenditure.getExpenditureType().name() );
        }
        updateExpenditureRequestElasticDto.amount( expenditure.getAmount() );
        if ( expenditure.getCurrency() != null ) {
            updateExpenditureRequestElasticDto.currency( expenditure.getCurrency().name() );
        }
        updateExpenditureRequestElasticDto.expendDetails( expenditure.getExpendDetails() );
        if ( expenditure.getStatus() != null ) {
            updateExpenditureRequestElasticDto.status( expenditure.getStatus().name() );
        }
        updateExpenditureRequestElasticDto.confirmDate( expenditure.getConfirmDate() );

        return updateExpenditureRequestElasticDto.build();
    }
}
