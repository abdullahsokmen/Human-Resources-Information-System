package com.group.dto.response;

import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayOffResponseDto {
    private Long createat;
    private String personalName;
    private String personalLastName;
    private String type;
    private Date startingDate;
    private Date requestDate;
    private Date endDate;
    private Date confirmDate;
    private Double span;
    private String status;
    private Long personalId;
    private Long dayOffRequestId;
}
