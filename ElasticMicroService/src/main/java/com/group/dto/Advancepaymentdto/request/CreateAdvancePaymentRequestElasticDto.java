package com.group.dto.Advancepaymentdto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdvancePaymentRequestElasticDto {
    private Long paymentRequestId;
    private String currency;
    private Double amount;
    private String advanceDetails;
    private String advancePaymentType;
    private Long personalId;
    private String personalName;
    private String personalLastName;

}
