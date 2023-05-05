package com.group.dto.response;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EStatus;
import com.group.repository.entity.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureResponseDto {

    private String personalName;
    private String personalLastName;
    private String status;
    private String expenditureType;
    private Double amount;
    private Date requestDate;
    private Date confirmDate;
    private String currency;
    private String expendDetails;
    private Long personalId;
    private Long expenditureRequestId;
    private Long createat;
}
