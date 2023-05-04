package com.group.dto.Dayoffdto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayOffUpdateRequestElasticDto {
    private Long dayOffRequestId;
    private String type;
    private Date startingDate;
    private Date confirmDate;
    private Date endDate;
    private Double span;
    private String status;
}
