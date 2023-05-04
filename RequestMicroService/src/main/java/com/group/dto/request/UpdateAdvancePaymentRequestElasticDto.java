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
public class UpdateAdvancePaymentRequestElasticDto {
    private Long paymentRequestId;
    private String advanceDetails;
    private String currency;
    private Double amount;
    private String status;
    private Date confirmDate;
}
