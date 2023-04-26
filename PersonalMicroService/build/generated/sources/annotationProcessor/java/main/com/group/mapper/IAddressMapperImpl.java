package com.group.mapper;

import com.group.dto.request.AddressCreateRequestDto;
import com.group.repository.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-26T14:30:48+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAddressMapperImpl implements IAddressMapper {

    @Override
    public Address toAddress(AddressCreateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Address.AddressBuilder<?, ?> address = Address.builder();

        address.town( dto.getTown() );
        address.city( dto.getCity() );
        address.country( dto.getCountry() );
        address.description( dto.getDescription() );

        return address.build();
    }
}
