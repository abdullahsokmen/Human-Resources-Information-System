package com.group.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IPersonalMapper {
    IPersonalMapper INSTANCE= Mappers.getMapper(IPersonalMapper.class);
}
