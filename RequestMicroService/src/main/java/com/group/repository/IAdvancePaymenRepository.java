package com.group.repository;

import com.group.repository.entity.AdvancePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdvancePaymenRepository extends JpaRepository<AdvancePayment,Long> {
}
