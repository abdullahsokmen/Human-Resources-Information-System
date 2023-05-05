package com.group.mapper;

import com.group.dto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.response.ExpenditureResponseDto;
import com.group.repository.entity.Expenditure;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-05T11:05:01+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IExpenditureMapperImpl implements IExpenditureMapper {

    @Override
    public Expenditure toExpenditure(CreateExpenditureRequestElasticDto dto) {
        if ( dto == null ) {
            return null;
        }

        Expenditure.ExpenditureBuilder<?, ?> expenditure = Expenditure.builder();

        expenditure.personalName( dto.getPersonalName() );
        expenditure.personalLastName( dto.getPersonalLastName() );
        expenditure.status( dto.getStatus() );
        expenditure.expenditureType( dto.getExpenditureType() );
        expenditure.amount( dto.getAmount() );
        expenditure.requestDate( dto.getRequestDate() );
        expenditure.confirmDate( dto.getConfirmDate() );
        expenditure.currency( dto.getCurrency() );
        expenditure.expendDetails( dto.getExpendDetails() );
        expenditure.personalId( dto.getPersonalId() );
        expenditure.expenditureRequestId( dto.getExpenditureRequestId() );

        return expenditure.build();
    }

    @Override
    public ExpenditureResponseDto fromExpenditure(Expenditure expenditure) {
        if ( expenditure == null ) {
            return null;
        }

        ExpenditureResponseDto.ExpenditureResponseDtoBuilder expenditureResponseDto = ExpenditureResponseDto.builder();

        expenditureResponseDto.personalName( expenditure.getPersonalName() );
        expenditureResponseDto.personalLastName( expenditure.getPersonalLastName() );
        if ( expenditure.getStatus() != null ) {
            expenditureResponseDto.status( expenditure.getStatus().name() );
        }
        if ( expenditure.getExpenditureType() != null ) {
            expenditureResponseDto.expenditureType( expenditure.getExpenditureType().name() );
        }
        expenditureResponseDto.amount( expenditure.getAmount() );
        expenditureResponseDto.requestDate( expenditure.getRequestDate() );
        expenditureResponseDto.confirmDate( expenditure.getConfirmDate() );
        if ( expenditure.getCurrency() != null ) {
            expenditureResponseDto.currency( expenditure.getCurrency().name() );
        }
        expenditureResponseDto.expendDetails( expenditure.getExpendDetails() );
        expenditureResponseDto.personalId( expenditure.getPersonalId() );
        expenditureResponseDto.expenditureRequestId( expenditure.getExpenditureRequestId() );
        expenditureResponseDto.createat( expenditure.getCreateat() );

        return expenditureResponseDto.build();
    }
}
