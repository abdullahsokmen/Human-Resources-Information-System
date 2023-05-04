package com.group.mapper;

import com.group.dto.Dayoffdto.request.DayOffSaveRequestElasticDto;
import com.group.repository.entity.DayOff;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-04T16:00:40+0300",
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
}
