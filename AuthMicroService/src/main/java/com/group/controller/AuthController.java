package com.group.controller;

import com.group.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;
@RequestMapping(AUTH)
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;
}
