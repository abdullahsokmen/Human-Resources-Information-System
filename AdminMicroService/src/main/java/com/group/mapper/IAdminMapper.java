package com.group.mapper;

import com.group.dto.SaveRequestDto;
import com.group.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAdminMapper {
    IAdminMapper INSTANCE= Mappers.getMapper(IAdminMapper.class);

    Admin toAdmin(final SaveRequestDto dto);
}
