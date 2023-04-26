package com.group.mapper;

import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.repository.entity.CompanyAdmin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-26T11:19:23+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class ICompanyAdminMapperImpl implements ICompanyAdminMapper {

    @Override
    public CompanyAdmin toCompanyAdmin(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        CompanyAdmin.CompanyAdminBuilder<?, ?> companyAdmin = CompanyAdmin.builder();

        companyAdmin.name( dto.getName() );
        companyAdmin.lastname( dto.getLastname() );
        companyAdmin.email( dto.getEmail() );
        companyAdmin.phone( dto.getPhone() );

        return companyAdmin.build();
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
        companyAdminResponseDto.companyName( companyAdmin.getCompanyName() );

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
        getAllCompanyAdminDetailsResponseDto.companyName( dto.getCompanyName() );

        return getAllCompanyAdminDetailsResponseDto.build();
    }
}
