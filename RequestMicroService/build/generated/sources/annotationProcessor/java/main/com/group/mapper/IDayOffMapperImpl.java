package com.group.mapper;

import com.group.dto.Dayoffdto.request.DayOffSaveRequestDto;
import com.group.dto.Dayoffdto.request.DayOffUpdateRequestDto;
import com.group.dto.Dayoffdto.response.DayOffResponseDto;
import com.group.repository.entity.DayOff;
import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T12:41:27+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IDayOffMapperImpl implements IDayOffMapper {

    @Override
    public DayOff toDayOff(DayOffSaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        DayOff.DayOffBuilder<?, ?> dayOff = DayOff.builder();

        if ( dto.getType() != null ) {
            dayOff.type( Enum.valueOf( EDayOffType.class, dto.getType() ) );
        }
        dayOff.startingDate( dto.getStartingDate() );
        dayOff.endDate( dto.getEndDate() );
        dayOff.span( dto.getSpan() );

        return dayOff.build();
    }

    @Override
    public DayOff toDayOff(DayOffUpdateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        DayOff.DayOffBuilder<?, ?> dayOff = DayOff.builder();

        dayOff.id( dto.getId() );
        if ( dto.getType() != null ) {
            dayOff.type( Enum.valueOf( EDayOffType.class, dto.getType() ) );
        }
        dayOff.startingDate( dto.getStartingDate() );
        dayOff.endDate( dto.getEndDate() );
        dayOff.span( dto.getSpan() );
        if ( dto.getStatus() != null ) {
            dayOff.status( Enum.valueOf( EStatus.class, dto.getStatus() ) );
        }

        return dayOff.build();
    }

    @Override
    public DayOffResponseDto fromDayOff(DayOff dayOff) {
        if ( dayOff == null ) {
            return null;
        }

        DayOffResponseDto.DayOffResponseDtoBuilder dayOffResponseDto = DayOffResponseDto.builder();

        if ( dayOff.getType() != null ) {
            dayOffResponseDto.type( dayOff.getType().name() );
        }
        dayOffResponseDto.requestDate( dayOff.getRequestDate() );
        if ( dayOff.getStatus() != null ) {
            dayOffResponseDto.status( dayOff.getStatus().name() );
        }
        dayOffResponseDto.startingDate( dayOff.getStartingDate() );
        dayOffResponseDto.endDate( dayOff.getEndDate() );
        dayOffResponseDto.span( dayOff.getSpan() );

        return dayOffResponseDto.build();
    }
}
