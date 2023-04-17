package com.group.controller;

import com.group.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {

    private final AdminService adminService;
}
