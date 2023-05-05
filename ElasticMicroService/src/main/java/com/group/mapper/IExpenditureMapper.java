package com.group.mapper;

import com.group.dto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.response.ExpenditureResponseDto;
import com.group.repository.entity.Expenditure;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IExpenditureMapper {
    IExpenditureMapper INSTANCE= Mappers.getMapper(IExpenditureMapper.class);

    Expenditure toExpenditure(final CreateExpenditureRequestElasticDto dto);

    ExpenditureResponseDto fromExpenditure(final Expenditure expenditure);
}
