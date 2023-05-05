package com.group.repository;

import com.group.repository.entity.AdvancePayment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdvancePaymentRepository extends ElasticsearchRepository<AdvancePayment,String> {

    Optional<AdvancePayment>findByPaymentRequestId(Long id);

    Iterable<AdvancePayment> findAllByPersonalId(Long personalId);
}
