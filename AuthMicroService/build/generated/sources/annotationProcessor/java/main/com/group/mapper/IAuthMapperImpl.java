package com.group.mapper;

import com.group.dto.request.RegisterRequestDto;
import com.group.dto.response.FindByIdResponseDto;
import com.group.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-14T15:01:11+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toAuth(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.name( dto.getName() );
        auth.mail( dto.getMail() );
        auth.surname( dto.getSurname() );
        auth.password( dto.getPassword() );

        return auth.build();
    }

    @Override
    public FindByIdResponseDto fromAuth(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        FindByIdResponseDto.FindByIdResponseDtoBuilder findByIdResponseDto = FindByIdResponseDto.builder();

        findByIdResponseDto.name( auth.getName() );
        findByIdResponseDto.surname( auth.getSurname() );
        findByIdResponseDto.phone( auth.getPhone() );

        return findByIdResponseDto.build();
    }
}
