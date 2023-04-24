package com.group.controller;

import com.group.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;
@RequiredArgsConstructor
@RequestMapping(COMPANY)
@RestController
public class CompanyController {
    private final CompanyService companyService;
}
