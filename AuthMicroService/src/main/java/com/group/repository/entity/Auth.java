package com.group.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

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
    private String mail;
    private String photoUrl;
    private String secondName;
    private String surname;
    private String birthDate;
    private String birthPlace;
    private String identity;
    private String phone;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Adress adress;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.PENDING;
    private String password;
}
