package com.group.dto.Expendituredto.response;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EStatus;
import com.group.repository.entity.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenditureResponseDto {
    private Long id;
    private String status;
    private String expenditureType;
    private Double amount;
    private Date requestDate;
    private Date confirmDate;
    private String currency;
    private String expendDetails;
}
