package com.group.mapper;

import com.group.dto.request.AddressCreateRequestDto;
import com.group.dto.request.CompanyAdminRegisterRequestDto;
import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.repository.entity.Address;
import com.group.repository.entity.CompanyAdmin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-27T17:29:15+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ICompanyAdminMapperImpl implements ICompanyAdminMapper {

    @Override
    public CompanyAdmin toCompanyAdmin(CompanyAdminRegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        CompanyAdmin.CompanyAdminBuilder<?, ?> companyAdmin = CompanyAdmin.builder();

        companyAdmin.photoUrl( dto.getPhotoUrl() );
        companyAdmin.name( dto.getName() );
        companyAdmin.lastname( dto.getLastname() );
        companyAdmin.birthDate( dto.getBirthDate() );
        companyAdmin.birthPlace( dto.getBirthPlace() );
        companyAdmin.identity( dto.getIdentity() );
        companyAdmin.dateOfStart( dto.getDateOfStart() );
        companyAdmin.major( dto.getMajor() );
        companyAdmin.department( dto.getDepartment() );
        companyAdmin.email( dto.getEmail() );
        companyAdmin.phone( dto.getPhone() );
        companyAdmin.companyId( dto.getCompanyId() );
        companyAdmin.address( addressCreateRequestDtoToAddress( dto.getAddress() ) );

        return companyAdmin.build();
    }

    @Override
    public RegisterRequestDto toRegisterRequestDto(CompanyAdmin companyAdmin) {
        if ( companyAdmin == null ) {
            return null;
        }

        RegisterRequestDto.RegisterRequestDtoBuilder registerRequestDto = RegisterRequestDto.builder();

        registerRequestDto.name( companyAdmin.getName() );
        registerRequestDto.email( companyAdmin.getEmail() );
        registerRequestDto.password( companyAdmin.getPassword() );

        return registerRequestDto.build();
    }

    @Override
    public CompanyAdminResponseDto fromCompanyAdmin(CompanyAdmin companyAdmin) {
        if ( companyAdmin == null ) {
            return null;
        }

        CompanyAdminResponseDto.CompanyAdminResponseDtoBuilder companyAdminResponseDto = CompanyAdminResponseDto.builder();

        companyAdminResponseDto.name( companyAdmin.getName() );
        companyAdminResponseDto.lastname( companyAdmin.getLastname() );
        companyAdminResponseDto.email( companyAdmin.getEmail() );
        companyAdminResponseDto.phone( companyAdmin.getPhone() );

        return companyAdminResponseDto.build();
    }

    @Override
    public GetAllCompanyAdminDetailsResponseDto fromCompanyAdminToGetAllDetails(CompanyAdmin dto) {
        if ( dto == null ) {
            return null;
        }

        GetAllCompanyAdminDetailsResponseDto.GetAllCompanyAdminDetailsResponseDtoBuilder getAllCompanyAdminDetailsResponseDto = GetAllCompanyAdminDetailsResponseDto.builder();

        getAllCompanyAdminDetailsResponseDto.id( dto.getId() );
        getAllCompanyAdminDetailsResponseDto.photoUrl( dto.getPhotoUrl() );
        getAllCompanyAdminDetailsResponseDto.name( dto.getName() );
        getAllCompanyAdminDetailsResponseDto.secondName( dto.getSecondName() );
        getAllCompanyAdminDetailsResponseDto.lastname( dto.getLastname() );
        getAllCompanyAdminDetailsResponseDto.birthDate( dto.getBirthDate() );
        getAllCompanyAdminDetailsResponseDto.birthPlace( dto.getBirthPlace() );
        getAllCompanyAdminDetailsResponseDto.identity( dto.getIdentity() );
        getAllCompanyAdminDetailsResponseDto.dateOfStart( dto.getDateOfStart() );
        getAllCompanyAdminDetailsResponseDto.major( dto.getMajor() );
        getAllCompanyAdminDetailsResponseDto.department( dto.getDepartment() );
        getAllCompanyAdminDetailsResponseDto.email( dto.getEmail() );
        getAllCompanyAdminDetailsResponseDto.phone( dto.getPhone() );

        return getAllCompanyAdminDetailsResponseDto.build();
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
