package com.group.controller;




import com.group.dto.request.*;

import com.group.dto.response.FindByIdResponseDto;
import com.group.dto.response.LoginResponse;
import com.group.exception.AuthManagerException;
import com.group.exception.EErrorType;

import com.group.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.group.constants.EndPoints.*;
@RequestMapping(AUTH)
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping(SAVE)
    public ResponseEntity<Long> register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PatchMapping(DEACTIVATE+BYID)
    public ResponseEntity<Boolean> deactivateById(@PathVariable Long id){
        return ResponseEntity.ok(authService.deactivateById(id));
    }
    @DeleteMapping(DELETE+BYID)
    public ResponseEntity<Boolean> deleteByAuthId(@PathVariable Long id){
        return ResponseEntity.ok(authService.deleteByAuthId(id));
    }
    @PostMapping(UPDATE+PASSWORD)
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePasswordRequestDto dto){
        return ResponseEntity.ok(authService.updatePassword(dto));
    }
    @PostMapping(UPDATE+MAIL)
    public ResponseEntity<Boolean> updateMail(@RequestBody UpdateMailRequestDto dto){
        return ResponseEntity.ok(authService.updateMail(dto));
    }
    @PostMapping(LOGIN) // login response a role eklenecek
    public ResponseEntity<LoginResponse> doLogin(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }
}
