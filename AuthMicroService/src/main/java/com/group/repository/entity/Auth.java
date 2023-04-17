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
@Table(name = "tblauth")
public class Auth extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String photoUrl;
    private String secondName;
    private String surname;
    private Date birthDate;
    private String birthPlace;
    @Column(unique = true)
    private String identity;
    @Column(unique = true)
    private String phone;

    private String activationCode;

    private String activatonCode;


    @Enumerated(EnumType.STRING)
    private ERole role;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.PENDING;
    private String password;
}
