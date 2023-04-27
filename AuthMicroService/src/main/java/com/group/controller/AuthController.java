package com.group.controller;




import com.group.dto.request.ActivateRequestDto;

import com.group.dto.request.LoginRequestDto;
import com.group.dto.request.UpdatePasswordRequestDto;
import com.group.dto.response.FindByIdResponseDto;
import com.group.dto.response.LoginResponse;
import com.group.exception.AuthManagerException;
import com.group.exception.EErrorType;
import com.group.dto.request.RegisterRequestDto;

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
    public ResponseEntity<Boolean> register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PatchMapping(DEACTIVATE+BYID) // diğer servislerden silinen kullanıcılarda bu metod çalıştırılacak
    public ResponseEntity<Boolean> deactivateById(@PathVariable Long id){
        return ResponseEntity.ok(authService.deactivateById(id));
    }
    @DeleteMapping(DELETE+BYID)
    public ResponseEntity<Boolean> deleteByAuthId(@PathVariable Long id){
        return ResponseEntity.ok(authService.deleteByAuthId(id));
    }

    @PostMapping(LOGIN) // login response a role eklenecek
    public ResponseEntity<LoginResponse> doLogin(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }
}
