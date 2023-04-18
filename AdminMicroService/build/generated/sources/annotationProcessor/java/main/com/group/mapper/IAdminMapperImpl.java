package com.group.mapper;

import com.group.dto.request.SaveRequestDto;
import com.group.dto.response.GetMinorInfoResponseDto;
import com.group.repository.entity.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-18T14:18:11+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAdminMapperImpl implements IAdminMapper {

    @Override
    public Admin toAdmin(SaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Admin.AdminBuilder<?, ?> admin = Admin.builder();

        admin.name( dto.getName() );
        admin.surname( dto.getSurname() );

        return admin.build();
    }

    @Override
    public GetMinorInfoResponseDto fromAdmin(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        GetMinorInfoResponseDto.GetMinorInfoResponseDtoBuilder getMinorInfoResponseDto = GetMinorInfoResponseDto.builder();

        getMinorInfoResponseDto.photoUrl( admin.getPhotoUrl() );
        getMinorInfoResponseDto.name( admin.getName() );
        getMinorInfoResponseDto.surname( admin.getSurname() );

        return getMinorInfoResponseDto.build();
    }
}
