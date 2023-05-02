package com.group.repository.entity;

import com.group.repository.entity.enums.EDayOffType;
import com.group.repository.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbldayoff")
public class DayOffRequest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
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

}
