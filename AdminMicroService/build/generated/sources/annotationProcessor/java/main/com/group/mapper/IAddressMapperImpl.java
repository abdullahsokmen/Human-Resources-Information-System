package com.group.mapper;

import com.group.dto.request.AddressUpdateRequestDto;
import com.group.repository.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-19T13:56:36+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAddressMapperImpl implements IAddressMapper {

    @Override
    public Address toAddress(AddressUpdateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.town( dto.getTown() );
        address.city( dto.getCity() );
        address.country( dto.getCountry() );
        address.description( dto.getDescription() );

        return address.build();
    }
}
