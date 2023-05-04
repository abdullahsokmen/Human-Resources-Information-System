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
    date = "2023-05-03T16:47:28+0300",
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

    @Override
    public DayOffSaveRequestElasticDto fromDayOffElastic(DayOff dayOff) {
        if ( dayOff == null ) {
            return null;
        }

        DayOffSaveRequestElasticDto.DayOffSaveRequestElasticDtoBuilder dayOffSaveRequestElasticDto = DayOffSaveRequestElasticDto.builder();

        if ( dayOff.getType() != null ) {
            dayOffSaveRequestElasticDto.type( dayOff.getType().name() );
        }
        dayOffSaveRequestElasticDto.startingDate( dayOff.getStartingDate() );
        dayOffSaveRequestElasticDto.endDate( dayOff.getEndDate() );
        dayOffSaveRequestElasticDto.span( dayOff.getSpan() );

        return dayOffSaveRequestElasticDto.build();
    }

    @Override
    public DayOffUpdateRequestElasticDto fromDayOffElasticUpdate(DayOff dayOff) {
        if ( dayOff == null ) {
            return null;
        }

        DayOffUpdateRequestElasticDto.DayOffUpdateRequestElasticDtoBuilder dayOffUpdateRequestElasticDto = DayOffUpdateRequestElasticDto.builder();

        dayOffUpdateRequestElasticDto.id( dayOff.getId() );
        if ( dayOff.getType() != null ) {
            dayOffUpdateRequestElasticDto.type( dayOff.getType().name() );
        }
        dayOffUpdateRequestElasticDto.startingDate( dayOff.getStartingDate() );
        dayOffUpdateRequestElasticDto.endDate( dayOff.getEndDate() );
        dayOffUpdateRequestElasticDto.span( dayOff.getSpan() );
        if ( dayOff.getStatus() != null ) {
            dayOffUpdateRequestElasticDto.status( dayOff.getStatus().name() );
        }

        return dayOffUpdateRequestElasticDto.build();
    }
}
