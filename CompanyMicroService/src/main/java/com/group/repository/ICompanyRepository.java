package com.group.repository;

import com.group.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRepository extends MongoRepository<Company,String >{
    boolean existsByEmail(String email);
    boolean existsByCompanyName(String companyName);

}
