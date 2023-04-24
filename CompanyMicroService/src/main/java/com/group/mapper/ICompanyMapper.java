package com.group.mapper;

import com.group.dto.request.CompanySaveRequestDto;
import com.group.dto.response.CompanyResponseDto;
import com.group.dto.response.GetAllCompanyDetailsResponseDto;
import com.group.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ICompanyMapper {

    ICompanyMapper INSTANCE= Mappers.getMapper(ICompanyMapper.class);

    Company toCompany(final CompanySaveRequestDto dto);
    CompanyResponseDto fromCompany(final Company company);
    GetAllCompanyDetailsResponseDto fromCompanyToGetAllDetails(final Company dto);
}
