package com.group.mapper;

import com.group.dto.request.AddressCreateRequestDto;
import com.group.repository.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAddressMapper {
    IAddressMapper INSTANCE = Mappers.getMapper(IAddressMapper.class);

    Address toAddress(final AddressCreateRequestDto dto);
}
