package com.group.dto.Advancepaymentdto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvancePaymentResponseDto {
    private Long id;
    private Date requestDate;
    private Date confirmDate;
    private String currency;
    private Double amount;
    private String advanceDetails;
    private String advancePaymentType;
    private String status;
    private Long PersonalId;
    private String name;
    private String lastname;
}
