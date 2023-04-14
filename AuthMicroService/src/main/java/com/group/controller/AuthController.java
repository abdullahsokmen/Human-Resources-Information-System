package com.group.controller;

import com.group.dto.request.RegisterRequestDto;
import com.group.repository.entity.Auth;
import com.group.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.group.constants.EndPoints.*;
@RequestMapping(AUTH)
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;


    @PostMapping(REGISTER)
    public ResponseEntity<Auth>register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(authService.saveDto(dto));

    }
}
