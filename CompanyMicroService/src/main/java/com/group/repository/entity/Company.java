package com.group.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Company extends BaseEntity{
    @Id
    private String id;
    private String companyName;
    private String title;
    private String mersisNo;
    private String taxNo;
    private String taxOffice;
    private String logoUrl;
    private String phone;
    private String address;
    private String email;
    private int numberOfEmployees;
    private String foundationYear;
    private Date contractStartDate;
    private Date contractEndDate;
    @Builder.Default
    private EStatus status=EStatus.ACTIVE;
}
