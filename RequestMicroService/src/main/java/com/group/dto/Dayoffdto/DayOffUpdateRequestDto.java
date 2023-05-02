package com.group.dto.Dayoffdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayOffUpdateRequestDto {

    private Long id;
    private String type;
    private Date startingDate;
    private Date endDate;
    private Double span;
    private String status;
}
