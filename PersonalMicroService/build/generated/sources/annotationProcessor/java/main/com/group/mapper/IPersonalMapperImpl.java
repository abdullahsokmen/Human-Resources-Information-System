package com.group.mapper;

import com.group.dto.request.AddressCreateRequestDto;
import com.group.dto.request.PersonalSaveRequestDto;
import com.group.dto.request.PersonalUpdateRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.GetAllDetailsResponseDto;
import com.group.dto.response.PersonalInfoResponseDto;
import com.group.dto.response.PersonalMinorDetailsResponseDto;
import com.group.repository.entity.Address;
import com.group.repository.entity.Personal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T16:46:45+0300",
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
        personal.major( dto.getMajor() );
        personal.department( dto.getDepartment() );
        personal.email( dto.getEmail() );
        personal.phone( dto.getPhone() );
        personal.companyId( dto.getCompanyId() );
        personal.salary( dto.getSalary() );
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
        personal.salary( dto.getSalary() );
        personal.address( addressCreateRequestDtoToAddress( dto.getAddress() ) );

        return personal.build();
    }

    @Override
    public RegisterRequestDto toRegisterRequestDto(Personal personal) {
        if ( personal == null ) {
            return null;
        }

        RegisterRequestDto.RegisterRequestDtoBuilder registerRequestDto = RegisterRequestDto.builder();

        registerRequestDto.name( personal.getName() );
        registerRequestDto.email( personal.getEmail() );
        registerRequestDto.password( personal.getPassword() );

        return registerRequestDto.build();
    }

    @Override
    public GetAllDetailsResponseDto toGetAllDetailsResponseDto(Personal personal) {
        if ( personal == null ) {
            return null;
        }

        GetAllDetailsResponseDto.GetAllDetailsResponseDtoBuilder getAllDetailsResponseDto = GetAllDetailsResponseDto.builder();

        getAllDetailsResponseDto.id( personal.getId() );
        getAllDetailsResponseDto.photoUrl( personal.getPhotoUrl() );
        getAllDetailsResponseDto.name( personal.getName() );
        getAllDetailsResponseDto.secondName( personal.getSecondName() );
        getAllDetailsResponseDto.lastname( personal.getLastname() );
        getAllDetailsResponseDto.birthDate( personal.getBirthDate() );
        getAllDetailsResponseDto.birthPlace( personal.getBirthPlace() );
        getAllDetailsResponseDto.identity( personal.getIdentity() );
        getAllDetailsResponseDto.dateOfStart( personal.getDateOfStart() );
        getAllDetailsResponseDto.major( personal.getMajor() );
        getAllDetailsResponseDto.department( personal.getDepartment() );
        getAllDetailsResponseDto.email( personal.getEmail() );
        getAllDetailsResponseDto.phone( personal.getPhone() );
        getAllDetailsResponseDto.companyId( personal.getCompanyId() );
        getAllDetailsResponseDto.address( personal.getAddress() );

        return getAllDetailsResponseDto.build();
    }

    @Override
    public PersonalInfoResponseDto fromPersonalInfo(Personal personal) {
        if ( personal == null ) {
            return null;
        }

        PersonalInfoResponseDto.PersonalInfoResponseDtoBuilder personalInfoResponseDto = PersonalInfoResponseDto.builder();

        personalInfoResponseDto.name( personal.getName() );
        personalInfoResponseDto.lastname( personal.getLastname() );
        personalInfoResponseDto.salary( personal.getSalary() );

        return personalInfoResponseDto.build();
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
