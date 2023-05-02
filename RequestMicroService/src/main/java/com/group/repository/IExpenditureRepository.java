package com.group.repository;

import com.group.repository.entity.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenditureRepository extends JpaRepository<Expenditure,Long> {
}
