package com.group.mapper;

import com.group.dto.request.PersonalSaveRequestDto;
import com.group.dto.request.PersonalUpdateRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.repository.entity.Personal;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IPersonalMapper {
    IPersonalMapper INSTANCE= Mappers.getMapper(IPersonalMapper.class);

    PersonalMinorDetailsResponseDto fromPersonal(final Personal personal);

    Personal toPersonal(final PersonalSaveRequestDto dto);
    Personal toPersonal(final PersonalUpdateRequestDto dto);
    RegisterRequestDto toRegisterRequestDto(final Personal personal);

}
