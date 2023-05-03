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
    @Builder.Default
    private Date requestDate = new Date();
    private Date endDate;
    private Date confirmDate;
    private Double span;
    @Builder.Default
    private EStatus status = EStatus.PENDING;
    private Long PersonalId;
    private Long dayOffRequestId;
}
