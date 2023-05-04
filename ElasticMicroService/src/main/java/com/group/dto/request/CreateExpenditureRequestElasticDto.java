package com.group.dto.request;


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
public class CreateExpenditureRequestElasticDto {
    private String personalName;
    private String personalLastName;
    private EStatus status;
    private ExpenditureType expenditureType;
    private Double amount;
    private Date requestDate;
    private Date confirmDate;
    private Currency currency;
    private String expendDetails;
    private Long personalId;
    private Long expenditureRequestId;
}
