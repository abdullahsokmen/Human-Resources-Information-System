package com.group.dto.Advancepaymentdto.request;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import com.group.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdvancePaymentRequestElasticDto {

    private String personalName;
    private String personalLastName;
    private Date requestDate;
    private Date confirmDate;
    private Currency currency;
    private Double amount;
    private String advanceDetails;
    private EAdvancePaymentType advancePaymentType;
    private EStatus status;
    private Long personalId;
    private Long paymentRequestId;

}
