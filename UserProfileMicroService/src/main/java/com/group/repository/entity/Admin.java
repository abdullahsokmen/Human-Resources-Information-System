package com.group.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "tbladmin")
public class Admin extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String photoUrl;
    private String secondName;
    private String surname;
    private String birthDate;
    private String birthPlace;
    private String identity;
    private String gender;
    private String phone;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Adress adress;
}
