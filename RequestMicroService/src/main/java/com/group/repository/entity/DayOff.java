package com.group.repository.entity;

import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
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
@Table(name = "tbldayoff")
public class DayOff extends BaseEntity{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String personalName;
        private String personalLastName;
        @Enumerated(EnumType.STRING)
        private EDayOffType type;
        private Date startingDate;
        @Builder.Default
        private Date requestDate = new Date();
        private Date endDate;
        private Date confirmDate;
        private Double span;
        @Enumerated(EnumType.STRING)
        @Builder.Default
        private EStatus status = EStatus.PENDING;
        private Long personalId;

}
