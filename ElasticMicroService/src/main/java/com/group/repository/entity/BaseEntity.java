package com.group.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder // base entity bir yere miras birakilacaksa anotasyonu superbuilder olmali.
public class BaseEntity {
    Long createat;
    Long updateat;
    boolean isactive;
}
