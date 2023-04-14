package com.group.controller;

import com.group.dto.request.UpdatePasswordRequestDto;
import com.group.exception.AuthServiceException;
import com.group.exception.EErrorType;
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

    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePasswordRequestDto dto){
        if (dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(EErrorType.INVALID_PARAMETER);
        return ResponseEntity.ok(authService.updatePassword(dto));
    }
}
