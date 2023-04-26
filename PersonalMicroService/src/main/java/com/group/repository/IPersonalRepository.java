package com.group.repository;

import com.group.repository.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonalRepository extends JpaRepository<Personal,Long> {

    boolean existsByEmail(String email);
    boolean existsByIdentity(String identity);
    boolean existsByPhone(String phone);
}
