package com.group.repository.entity;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EStatus;
import com.group.repository.entity.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblexpenditure")
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status =EStatus.PENDING;
    @Enumerated(EnumType.STRING)
    private ExpenditureType expenditureType;
    private Double amount;
    private Date startingDate;
    @Builder.Default
    private Date requestDate = new Date();
    private Date endDate;
    private Date confirmDate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String expendDetails;
}
