package com.group.mapper;

import com.group.dto.Dayoffdto.DayOffSaveRequestDto;
import com.group.dto.Dayoffdto.DayOffUpdateRequestDto;
import com.group.repository.entity.DayOff;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IDayOffMapper {

    IDayOffMapper INSTANCE = Mappers.getMapper(IDayOffMapper.class);

    DayOff toDayOff(final DayOffSaveRequestDto dto);
    DayOff toDayOff(final DayOffUpdateRequestDto dto);
}
