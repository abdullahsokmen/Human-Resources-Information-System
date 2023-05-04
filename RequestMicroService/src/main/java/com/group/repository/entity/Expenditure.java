package com.group.repository.entity;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EStatus;
import com.group.repository.entity.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblexpenditure")
public class Expenditure extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String personalName;
    private String personalLastName;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status =EStatus.PENDING;
    @Enumerated(EnumType.STRING)
    private ExpenditureType expenditureType;
    private Double amount;
    @Builder.Default
    private Date requestDate = new Date();
    private Date confirmDate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String expendDetails;
    private Long personalId;
}
