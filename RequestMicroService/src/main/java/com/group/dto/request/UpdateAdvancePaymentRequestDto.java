package com.group.dto.request;

import com.group.repository.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdvancePaymentRequestDto {
    private Long id;
    private String advanceDetails;
    private String  currency;
    private Double amount;
}
