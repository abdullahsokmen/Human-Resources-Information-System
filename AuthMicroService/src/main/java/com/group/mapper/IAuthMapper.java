package com.group.mapper;

import com.group.dto.request.FindByIdRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.request.SaveRequestDto;
import com.group.dto.response.FindByIdResponseDto;
import com.group.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);

    FindByIdResponseDto fromAuth(final Auth auth);

    SaveRequestDto toSaveRequestDto(final Auth auth);
}
