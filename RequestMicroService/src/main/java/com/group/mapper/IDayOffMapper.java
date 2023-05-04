package com.group.mapper;

import com.group.dto.Dayoffdto.request.DayOffSaveRequestDto;
import com.group.dto.Dayoffdto.request.DayOffSaveRequestElasticDto;
import com.group.dto.Dayoffdto.request.DayOffUpdateRequestDto;
import com.group.dto.Dayoffdto.request.DayOffUpdateRequestElasticDto;
import com.group.dto.Dayoffdto.response.DayOffResponseDto;
import com.group.repository.entity.DayOff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IDayOffMapper {

    IDayOffMapper INSTANCE = Mappers.getMapper(IDayOffMapper.class);

    DayOff toDayOff(final DayOffSaveRequestDto dto);
    DayOff toDayOff(final DayOffUpdateRequestDto dto);

    DayOffResponseDto fromDayOff(final DayOff dayOff);

    @Mapping(source = "id",target = "dayOffRequestId")
    DayOffSaveRequestElasticDto fromDayOffElastic(final DayOff dayOff);
    @Mapping(source = "id",target = "dayOffRequestId")
    DayOffUpdateRequestElasticDto fromDayOffElasticUpdate(final DayOff dayOff);

}
