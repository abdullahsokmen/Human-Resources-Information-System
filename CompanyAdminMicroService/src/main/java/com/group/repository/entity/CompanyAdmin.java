package com.group.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tblCompanyAdmin")
public class CompanyAdmin extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photoUrl;
    private String name;
    private String secondName;
    private String lastname;
    private Date birthDate;
    private String birthPlace;
    private String identity;
    private Date dateOfStart;
    private String major;
    private String department;
    private String email;
    private String phone;
    private String companyName;
    private String activationCode;
}
