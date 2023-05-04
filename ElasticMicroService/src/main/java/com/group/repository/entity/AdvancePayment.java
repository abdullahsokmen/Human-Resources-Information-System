package com.group.repository.entity;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import com.group.repository.entity.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "advancepayment")
public class AdvancePayment extends BaseEntity{

    @Id
    private String id;
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
