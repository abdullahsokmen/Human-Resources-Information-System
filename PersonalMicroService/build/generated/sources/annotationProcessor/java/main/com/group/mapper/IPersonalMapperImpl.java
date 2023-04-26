package com.group.mapper;

import com.group.dto.request.AddressCreateRequestDto;
import com.group.dto.request.PersonalSaveRequestDto;
import com.group.dto.request.PersonalUpdateRequestDto;
import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.repository.entity.Address;
import com.group.repository.entity.Personal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-26T14:30:48+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IPersonalMapperImpl implements IPersonalMapper {

    @Override
    public PersonalMinorDetailsResponseDto fromPersonal(Personal personal) {
        if ( personal == null ) {
            return null;
        }

        PersonalMinorDetailsResponseDto.PersonalMinorDetailsResponseDtoBuilder personalMinorDetailsResponseDto = PersonalMinorDetailsResponseDto.builder();

        personalMinorDetailsResponseDto.name( personal.getName() );
        personalMinorDetailsResponseDto.lastname( personal.getLastname() );
        personalMinorDetailsResponseDto.phone( personal.getPhone() );
        personalMinorDetailsResponseDto.email( personal.getEmail() );
        personalMinorDetailsResponseDto.companyId( personal.getCompanyId() );

        return personalMinorDetailsResponseDto.build();
    }

    @Override
    public Personal toPersonal(PersonalSaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Personal.PersonalBuilder<?, ?> personal = Personal.builder();

        personal.photoUrl( dto.getPhotoUrl() );
        personal.name( dto.getName() );
        personal.secondName( dto.getSecondName() );
        personal.lastname( dto.getLastname() );
        personal.birthDate( dto.getBirthDate() );
        personal.birthPlace( dto.getBirthPlace() );
        personal.identity( dto.getIdentity() );
        personal.dateOfStart( dto.getDateOfStart() );
        personal.dateOfEnd( dto.getDateOfEnd() );
        personal.major( dto.getMajor() );
        personal.department( dto.getDepartment() );
        personal.email( dto.getEmail() );
        personal.phone( dto.getPhone() );
        personal.companyId( dto.getCompanyId() );
        personal.address( addressCreateRequestDtoToAddress( dto.getAddress() ) );

        return personal.build();
    }

    @Override
    public Personal toPersonal(PersonalUpdateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Personal.PersonalBuilder<?, ?> personal = Personal.builder();

        personal.id( dto.getId() );
        personal.photoUrl( dto.getPhotoUrl() );
        personal.name( dto.getName() );
        personal.secondName( dto.getSecondName() );
        personal.lastname( dto.getLastname() );
        personal.birthDate( dto.getBirthDate() );
        personal.birthPlace( dto.getBirthPlace() );
        personal.identity( dto.getIdentity() );
        personal.major( dto.getMajor() );
        personal.department( dto.getDepartment() );
        personal.email( dto.getEmail() );
        personal.phone( dto.getPhone() );
        personal.address( addressCreateRequestDtoToAddress( dto.getAddress() ) );

        return personal.build();
    }

    protected Address addressCreateRequestDtoToAddress(AddressCreateRequestDto addressCreateRequestDto) {
        if ( addressCreateRequestDto == null ) {
            return null;
        }

        Address.AddressBuilder<?, ?> address = Address.builder();

        address.town( addressCreateRequestDto.getTown() );
        address.city( addressCreateRequestDto.getCity() );
        address.country( addressCreateRequestDto.getCountry() );
        address.description( addressCreateRequestDto.getDescription() );

        return address.build();
    }
}
