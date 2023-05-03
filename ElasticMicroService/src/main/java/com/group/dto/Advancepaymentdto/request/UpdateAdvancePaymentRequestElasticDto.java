package com.group.dto.Advancepaymentdto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdvancePaymentRequestElasticDto {
    private Long paymentRequestId;
    private String advanceDetails;
    private String  currency;
    private Double amount;
}
