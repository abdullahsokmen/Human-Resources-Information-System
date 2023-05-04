package com.group.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExpenditureRequestElasticDto {
    private Long expenditureRequestId;
    private String expenditureType;
    private Double amount;
    private String currency;
    private String expendDetails;
    private String status;
    private Date confirmDate;
}
