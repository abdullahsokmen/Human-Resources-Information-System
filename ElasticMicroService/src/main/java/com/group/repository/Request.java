package com.group.repository;

import com.group.repository.entity.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "request")
public class Request extends BaseEntity {
    @Id
    private Long id;
}
