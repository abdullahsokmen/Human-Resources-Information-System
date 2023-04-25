package com.group.mapper;

import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.CompanyAdminResponseDto;
import com.group.dto.response.GetAllCompanyAdminDetailsResponseDto;
import com.group.repository.entity.CompanyAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ICompanyAdminMapper {

    ICompanyAdminMapper INSTANCE= Mappers.getMapper(ICompanyAdminMapper.class);

    CompanyAdmin toCompanyAdmin(final RegisterRequestDto dto);

    CompanyAdminResponseDto fromCompanyAdmin(final CompanyAdmin companyAdmin);

    GetAllCompanyAdminDetailsResponseDto fromCompanyAdminToGetAllDetails(final CompanyAdmin dto);
}
