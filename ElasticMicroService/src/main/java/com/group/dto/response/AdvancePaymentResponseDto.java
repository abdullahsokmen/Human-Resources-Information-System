package com.group.dto.response;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import com.group.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvancePaymentResponseDto {
    private String personalName;
    private String personalLastName;
    private Date requestDate;
    private Date confirmDate;
    private String currency;
    private Double amount;
    private String advanceDetails;
    private String advancePaymentType;
    private String status;
    private Long personalId;
    private Long paymentRequestId;
    private Long createat;
}
