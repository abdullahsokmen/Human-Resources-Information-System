package com.group.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbladress")
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String town;
    private String city;
    private String country;
    private String description;
    @OneToOne
    @JsonIgnore
    private Admin admin;
}
