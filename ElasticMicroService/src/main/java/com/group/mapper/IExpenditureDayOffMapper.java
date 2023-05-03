package com.group.mapper;

import com.group.dto.Expendituredto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.Expendituredto.request.UpdateExpenditureRequestElasticDto;
import com.group.dto.Expendituredto.response.ExpenditureResponseDto;
import com.group.repository.entity.Expenditure;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IExpenditureDayOffMapper {
    IExpenditureDayOffMapper INSTANCE= Mappers.getMapper(IExpenditureDayOffMapper.class);

    Expenditure toExpenditure(final CreateExpenditureRequestElasticDto dto);
    Expenditure toExpenditure(final UpdateExpenditureRequestElasticDto dto);

    ExpenditureResponseDto fromExpenditure(final Expenditure expenditure);
}
