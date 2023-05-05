package com.group.controller;


import com.group.dto.request.EditProfileRequestDto;
import com.group.dto.request.ResetPasswordRequestDto;
import com.group.dto.request.SaveRequestDto;
import com.group.dto.response.GetAllResponseDto;
import com.group.dto.response.GetMinorInfoResponseDto;


import com.group.dto.request.UpdateRequestDto;

import com.group.repository.entity.Admin;
import com.group.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.group.constants.EndPoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {

    private final AdminService adminService;

    @PostMapping(SAVE)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> createAdmin(@RequestBody @Valid SaveRequestDto dto){
        return ResponseEntity.ok(adminService.createAdmin(dto));
    }
    @GetMapping(GETALLDETAIL)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GetAllResponseDto>getAllDetail(@RequestParam Long id){
        return ResponseEntity.ok(adminService.getAllDetail(id));
    }
    @GetMapping(GETMINOR)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<GetMinorInfoResponseDto> getMinorInformation(@RequestParam Long id){
        return ResponseEntity.ok(adminService.getMinorInformation(id));
    }

    @PutMapping(UPDATE)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> update(@RequestBody @Valid UpdateRequestDto dto){
        return ResponseEntity.ok(adminService.updateDto(dto));
    }
    @PutMapping(EDITPROFILE)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean>editProfile(@RequestBody EditProfileRequestDto dto){
        return ResponseEntity.ok(adminService.editProfile(dto));
    }

    @DeleteMapping(DELETE+BYID)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> deleteAdminById(@PathVariable Long id){
        return ResponseEntity.ok(adminService.deleteAdminById(id));
    }

    @PostMapping(FORGOT)
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequestDto dto){
        adminService.resetPassword(dto);
        return ResponseEntity.ok().build();
    }

}
