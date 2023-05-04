package com.group.repository.entity;

import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "dayoff")
public class DayOff extends BaseEntity{
    @Id
    private String id;
    private String personalName;
    private String personalLastName;
    private EDayOffType type;
    private Date startingDate;
    private Date requestDate;
    private Date endDate;
    private Date confirmDate;
    private Double span;
    private EStatus status;
    private Long personalId;
    private Long dayOffRequestId;
}
