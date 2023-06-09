package com.group.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateExpenditureRequestDto {
    private String expenditureType;
    private Double amount;
    private String currency;
    private String expendDetails;
    private Long PersonalId;
}
