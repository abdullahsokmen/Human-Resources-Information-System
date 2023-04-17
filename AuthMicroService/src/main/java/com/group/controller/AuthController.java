package com.group.controller;


import com.group.dto.request.UpdatePasswordRequestDto;
import com.group.dto.response.FindByIdResponseDto;
import com.group.exception.AuthServiceException;
import com.group.exception.EErrorType;
import com.group.dto.request.RegisterRequestDto;

import com.group.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.group.constants.EndPoints.*;
@RequestMapping(AUTH)
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;


    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePasswordRequestDto dto) {
        if (dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(EErrorType.INVALID_PARAMETER);
        return ResponseEntity.ok(authService.updatePassword(dto));
    }
    @PostMapping(REGISTER)
    public ResponseEntity<Boolean>register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));

    }
    @GetMapping(FINDBYID)
    public ResponseEntity<FindByIdResponseDto> findById(@RequestParam Long id){
        return ResponseEntity.ok(authService.findByIdResponseDto(id));
    }
    @PatchMapping(DEACTIVATE+BYID)
    public ResponseEntity<Boolean> deactivateById(@PathVariable Long id){
        return ResponseEntity.ok(authService.deactivateById(id));
    }
    @DeleteMapping(DELETE+BYID)
    public ResponseEntity<Boolean> deleteByAuthId(@PathVariable Long id){
        return ResponseEntity.ok(authService.deleteByAuthId(id));
    }

}
