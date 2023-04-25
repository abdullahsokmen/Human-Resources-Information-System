package com.group.controller;

import com.group.CompanyAdminService;
import com.group.dto.request.RegisterRequestDto;
import com.group.service.CompanyAdminServiceClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;
@RestController
@RequestMapping(COMPANYADMIN)
@RequiredArgsConstructor
public class CompanyAdminController {
    private final CompanyAdminServiceClass companyAdminService;

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(companyAdminService.register(dto));
    }
}
