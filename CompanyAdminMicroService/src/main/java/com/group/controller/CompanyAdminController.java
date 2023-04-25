package com.group.controller;

import com.group.CompanyAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;
@RestController
@RequestMapping(COMPANYADMIN)
@RequiredArgsConstructor
public class CompanyAdminController {
    private final CompanyAdminService companyAdminService;
}
