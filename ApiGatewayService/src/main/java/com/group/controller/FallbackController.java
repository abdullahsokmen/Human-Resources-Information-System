package com.group.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/authservice")
    public ResponseEntity<String>fallbackAuth(){
        return ResponseEntity.ok("Auth service is currently unavailable");
    }
    @GetMapping("/adminservice")
    public ResponseEntity<String>fallbackAdmin(){
        return ResponseEntity.ok("Admin service currently unavalible");
    }
    @GetMapping("/companyadminservice")
    public ResponseEntity<String>fallbackCompanyAdmin(){
        return ResponseEntity.ok("Company Admin service currently unavalible");
    }
    @GetMapping("/companyservice")
    public ResponseEntity<String>fallbackCompany(){
        return ResponseEntity.ok("Company service currently unavalible");
    }
    @GetMapping("/personalservice")
    public ResponseEntity<String>fallbackPersonal(){
        return ResponseEntity.ok("Personal service currently unavalible");
    }
    @GetMapping("/requestservice")
    public ResponseEntity<String>fallbackRequest(){
        return ResponseEntity.ok("Request service currently unavalible");
    }
    @GetMapping("/elasticservice")
    public ResponseEntity<String>fallbackElastic(){
        return ResponseEntity.ok("Elastic service currently unavalible");
    }
}
