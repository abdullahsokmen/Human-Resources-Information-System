package com.group.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IRequestRepository extends ElasticsearchRepository<Request,Long> {
}
