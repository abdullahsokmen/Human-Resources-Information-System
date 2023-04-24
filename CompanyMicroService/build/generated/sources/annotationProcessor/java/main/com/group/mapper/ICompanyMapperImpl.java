package com.group.mapper;

import com.group.dto.request.CompanySaveRequestDto;
import com.group.dto.response.CompanyResponseDto;
import com.group.dto.response.GetAllCompanyDetailsResponseDto;
import com.group.repository.entity.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T15:14:41+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ICompanyMapperImpl implements ICompanyMapper {

    @Override
    public Company toCompany(CompanySaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Company.CompanyBuilder<?, ?> company = Company.builder();

        company.companyName( dto.getCompanyName() );
        company.title( dto.getTitle() );
        company.mersisNo( dto.getMersisNo() );
        company.taxNo( dto.getTaxNo() );
        company.taxOffice( dto.getTaxOffice() );
        company.logoUrl( dto.getLogoUrl() );
        company.phone( dto.getPhone() );
        company.address( dto.getAddress() );
        company.email( dto.getEmail() );
        company.foundationYear( dto.getFoundationYear() );

        return company.build();
    }

    @Override
    public CompanyResponseDto fromCompany(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyResponseDto.CompanyResponseDtoBuilder companyResponseDto = CompanyResponseDto.builder();

        companyResponseDto.companyName( company.getCompanyName() );
        companyResponseDto.title( company.getTitle() );
        companyResponseDto.phone( company.getPhone() );
        companyResponseDto.address( company.getAddress() );
        companyResponseDto.email( company.getEmail() );

        return companyResponseDto.build();
    }

    @Override
    public GetAllCompanyDetailsResponseDto fromCompanyToGetAllDetails(Company dto) {
        if ( dto == null ) {
            return null;
        }

        GetAllCompanyDetailsResponseDto.GetAllCompanyDetailsResponseDtoBuilder getAllCompanyDetailsResponseDto = GetAllCompanyDetailsResponseDto.builder();

        getAllCompanyDetailsResponseDto.id( dto.getId() );
        getAllCompanyDetailsResponseDto.companyName( dto.getCompanyName() );
        getAllCompanyDetailsResponseDto.title( dto.getTitle() );
        getAllCompanyDetailsResponseDto.mersisNo( dto.getMersisNo() );
        getAllCompanyDetailsResponseDto.taxNo( dto.getTaxNo() );
        getAllCompanyDetailsResponseDto.taxOffice( dto.getTaxOffice() );
        getAllCompanyDetailsResponseDto.logoUrl( dto.getLogoUrl() );
        getAllCompanyDetailsResponseDto.phone( dto.getPhone() );
        getAllCompanyDetailsResponseDto.address( dto.getAddress() );
        getAllCompanyDetailsResponseDto.email( dto.getEmail() );
        getAllCompanyDetailsResponseDto.numberOfEmployees( dto.getNumberOfEmployees() );
        getAllCompanyDetailsResponseDto.foundationYear( dto.getFoundationYear() );
        getAllCompanyDetailsResponseDto.contractStartDate( dto.getContractStartDate() );
        getAllCompanyDetailsResponseDto.contractEndDate( dto.getContractEndDate() );

        return getAllCompanyDetailsResponseDto.build();
    }
}
