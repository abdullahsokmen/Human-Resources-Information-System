package com.group.mapper;

import com.group.dto.request.CreateExpenditureRequestDto;
import com.group.dto.request.CreateExpenditureRequestElasticDto;
import com.group.dto.request.UpdateExpenditureRequestElasticDto;
import com.group.repository.entity.Expenditure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IExpenditureDayOffMapper {
    IExpenditureDayOffMapper INSTANCE= Mappers.getMapper(IExpenditureDayOffMapper.class);

    Expenditure toExpenditure(final CreateExpenditureRequestDto dto);
    @Mapping(source = "id",target = "expenditureRequestId")
    CreateExpenditureRequestElasticDto fromExpenditureElastic(final Expenditure expenditure);
    @Mapping(source = "id",target = "expenditureRequestId")
    UpdateExpenditureRequestElasticDto fromExpenditureElasticUpdate(final Expenditure expenditure);
}
