package com.group.repository;

import com.group.repository.entity.CompanyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyMicroServiceRepository extends JpaRepository<CompanyAdmin,Long> {
}
