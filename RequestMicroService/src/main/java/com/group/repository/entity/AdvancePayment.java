package com.group.repository.entity;

import com.group.repository.entity.enums.Currency;
import com.group.repository.entity.enums.EAdvancePaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbladvance")
public class AdvancePayment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Builder.Default
    private Date requestDate = new Date();
    private Date confirmDate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double amount;
    private String advanceDetails;
    @Enumerated(EnumType.STRING)
    private EAdvancePaymentType advancePaymentType;

}
