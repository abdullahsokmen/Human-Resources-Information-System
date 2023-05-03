package com.group.repository.entity;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EStatus;
import com.group.repository.entity.enums.ExpenditureType;
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
@Document(indexName = "expenditure")
public class Expenditure extends BaseEntity{
    @Id
    private String id;
    private String personalName;
    private String personalLastName;
    @Builder.Default
    private EStatus status =EStatus.PENDING;
    private ExpenditureType expenditureType;
    private Double amount;
    @Builder.Default
    private Date requestDate = new Date();
    private Date confirmDate;
    private Currency currency;
    private String expendDetails;
    private Long PersonalId;
    private Long expenditureRequestId;
}
