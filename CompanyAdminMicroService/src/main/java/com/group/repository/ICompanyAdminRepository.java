package com.group.repository;

import com.group.repository.entity.CompanyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyAdminRepository extends JpaRepository<CompanyAdmin,Long> {
    boolean existsByEmail(String email);
    boolean existsByIdentity(String identity);
    boolean existsByPhone(String phone);

    Optional<CompanyAdmin> findByAuthId(Long authId);
}
