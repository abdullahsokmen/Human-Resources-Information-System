package com.group.repository;

import com.group.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
