package com.group.repository;

import com.group.repository.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long> {

    boolean existsByEmail(String email);
    Optional<Admin> findByAuthId(Long authId);

}
