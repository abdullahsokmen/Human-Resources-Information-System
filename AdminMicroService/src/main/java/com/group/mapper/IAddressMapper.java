package com.group.mapper;

import com.group.dto.request.AddressUpdateRequestDto;
import com.group.repository.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAddressMapper {
    IAddressMapper INSTANCE= Mappers.getMapper(IAddressMapper.class);

    Address toAddress(final AddressUpdateRequestDto dto);
}
