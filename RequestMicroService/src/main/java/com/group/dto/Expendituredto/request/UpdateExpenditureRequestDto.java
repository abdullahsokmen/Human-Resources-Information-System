package com.group.dto.Expendituredto.request;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExpenditureRequestDto {
    private Long id;
    private String expenditureType;
    private Double amount;
    private String currency;
    private String expendDetails;
}
