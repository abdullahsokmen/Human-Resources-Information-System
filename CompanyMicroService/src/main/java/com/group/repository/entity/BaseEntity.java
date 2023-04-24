package com.group.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder

public class BaseEntity {
    Long createDate;
    Long updateDate;

}
