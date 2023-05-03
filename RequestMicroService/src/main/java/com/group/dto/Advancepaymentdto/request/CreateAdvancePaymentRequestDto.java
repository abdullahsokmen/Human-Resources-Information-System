package com.group.dto.Advancepaymentdto.request;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdvancePaymentRequestDto {
    private String currency;
    private Double amount;
    private String advanceDetails;
    private String advancePaymentType;
    private Long PersonalId;
}
