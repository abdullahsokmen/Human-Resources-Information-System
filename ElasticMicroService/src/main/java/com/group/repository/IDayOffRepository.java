package com.group.repository;

import com.group.repository.entity.DayOff;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDayOffRepository extends ElasticsearchRepository<DayOff,String > {
    Optional<DayOff>findByDayOffRequestId(Long id);
}
