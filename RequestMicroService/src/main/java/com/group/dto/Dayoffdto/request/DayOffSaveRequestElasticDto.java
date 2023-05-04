package com.group.dto.Dayoffdto.request;


import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayOffSaveRequestElasticDto {

    private String personalName;
    private String personalLastName;
    private EDayOffType type;
    private Date startingDate;
    private Date requestDate;
    private Date endDate;
    private Date confirmDate;
    private Double span;
    private EStatus status;
    private Long personalId;
    private Long dayOffRequestId;
}
