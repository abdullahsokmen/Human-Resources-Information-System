package com.group.dto.Dayoffdto.response;

import com.group.repository.entity.enums.EDayOffType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayOffResponseDto {
    private String type;
    private Date requestDate;
    private String status;
    private Date startingDate;
    private Date endDate;
    private Double span;
    private Long PersonalId;
    private String name;
    private String lastname;

}
