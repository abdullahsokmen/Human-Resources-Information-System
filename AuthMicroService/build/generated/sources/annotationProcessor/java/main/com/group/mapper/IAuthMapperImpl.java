package com.group.mapper;

import com.group.dto.request.RegisterRequestDto;
import com.group.dto.request.SaveRequestDto;
import com.group.dto.response.FindByIdResponseDto;
import com.group.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-27T13:08:26+0300",
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

        auth.email( dto.getEmail() );
        auth.name( dto.getName() );
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
        findByIdResponseDto.email( auth.getEmail() );

        return findByIdResponseDto.build();
    }

    @Override
    public SaveRequestDto toSaveRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        SaveRequestDto.SaveRequestDtoBuilder saveRequestDto = SaveRequestDto.builder();

        saveRequestDto.name( auth.getName() );
        saveRequestDto.surname( auth.getSurname() );
        saveRequestDto.password( auth.getPassword() );

        return saveRequestDto.build();
    }
}
