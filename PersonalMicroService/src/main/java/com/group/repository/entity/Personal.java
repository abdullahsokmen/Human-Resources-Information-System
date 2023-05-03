package com.group.repository.entity;

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
@Table(name = "tblpersonal")
public class Personal extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String photoUrl;
    private String name;
    private String secondName;
    private String lastname;
    private Date birthDate;
    private String birthPlace;
    private String identity;
    private Date dateOfStart;
    private Date dateOfEnd;
    private String major;
    private String department;
    private String email;
    private String phone;
    private String companyId;
    private Long authId;
    private Double salary;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.ACTIVE;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Address address;


}
