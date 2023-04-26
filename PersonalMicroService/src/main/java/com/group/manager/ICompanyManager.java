package com.group.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.group.constants.EndPoints.BYID;
import static com.group.constants.EndPoints.EXIST;

@FeignClient(url = "http://localhost:9092/api/v1/company",decode404 = true,name = "personal-company")
public interface ICompanyManager {

    @GetMapping(EXIST+BYID)
    public ResponseEntity<Boolean> exitsById(@PathVariable String id);
}
