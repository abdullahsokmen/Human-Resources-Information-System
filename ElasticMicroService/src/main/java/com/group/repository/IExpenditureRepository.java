package com.group.repository;

import com.group.repository.entity.Expenditure;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExpenditureRepository extends ElasticsearchRepository<Expenditure,String > {
    Optional<Expenditure>findByExpenditureRequestId(Long id);

    Iterable<Expenditure> findAllByPersonalId(Long personalId);
}
