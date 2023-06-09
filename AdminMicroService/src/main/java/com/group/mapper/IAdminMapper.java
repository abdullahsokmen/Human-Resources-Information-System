package com.group.mapper;

import com.group.dto.request.RegisterRequestDto;
import com.group.dto.request.SaveRequestDto;
import com.group.dto.response.GetAllResponseDto;
import com.group.dto.response.GetMinorInfoResponseDto;
import com.group.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAdminMapper {
    IAdminMapper INSTANCE= Mappers.getMapper(IAdminMapper.class);

    Admin toAdmin(final SaveRequestDto dto);
    RegisterRequestDto toRegisterRequestDto(final Admin admin);

    GetMinorInfoResponseDto fromAdmin(final Admin admin);

    GetAllResponseDto toGetAllResponseDto(final Admin admin);
}
