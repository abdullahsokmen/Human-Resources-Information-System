package com.group.mapper;

import com.group.dto.request.DayOffSaveRequestElasticDto;
import com.group.dto.response.DayOffResponseDto;
import com.group.repository.entity.DayOff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-05T11:05:01+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IDayOffMapperImpl implements IDayOffMapper {

    @Override
    public DayOff toDayOff(DayOffSaveRequestElasticDto dto) {
        if ( dto == null ) {
            return null;
        }

        DayOff.DayOffBuilder<?, ?> dayOff = DayOff.builder();

        dayOff.personalName( dto.getPersonalName() );
        dayOff.personalLastName( dto.getPersonalLastName() );
        dayOff.type( dto.getType() );
        dayOff.startingDate( dto.getStartingDate() );
        dayOff.requestDate( dto.getRequestDate() );
        dayOff.endDate( dto.getEndDate() );
        dayOff.confirmDate( dto.getConfirmDate() );
        dayOff.span( dto.getSpan() );
        dayOff.status( dto.getStatus() );
        dayOff.personalId( dto.getPersonalId() );
        dayOff.dayOffRequestId( dto.getDayOffRequestId() );

        return dayOff.build();
    }

    @Override
    public DayOffResponseDto toDayOffResponseDto(DayOff dayOff) {
        if ( dayOff == null ) {
            return null;
        }

        DayOffResponseDto.DayOffResponseDtoBuilder dayOffResponseDto = DayOffResponseDto.builder();

        dayOffResponseDto.createat( dayOff.getCreateat() );
        dayOffResponseDto.personalName( dayOff.getPersonalName() );
        dayOffResponseDto.personalLastName( dayOff.getPersonalLastName() );
        if ( dayOff.getType() != null ) {
            dayOffResponseDto.type( dayOff.getType().name() );
        }
        dayOffResponseDto.startingDate( dayOff.getStartingDate() );
        dayOffResponseDto.requestDate( dayOff.getRequestDate() );
        dayOffResponseDto.endDate( dayOff.getEndDate() );
        dayOffResponseDto.confirmDate( dayOff.getConfirmDate() );
        dayOffResponseDto.span( dayOff.getSpan() );
        if ( dayOff.getStatus() != null ) {
            dayOffResponseDto.status( dayOff.getStatus().name() );
        }
        dayOffResponseDto.personalId( dayOff.getPersonalId() );
        dayOffResponseDto.dayOffRequestId( dayOff.getDayOffRequestId() );

        return dayOffResponseDto.build();
    }
}
