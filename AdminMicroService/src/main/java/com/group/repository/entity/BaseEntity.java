package com.group.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@MappedSuperclass
public class BaseEntity {
    Long createDate;
    Long updateDate;

}
