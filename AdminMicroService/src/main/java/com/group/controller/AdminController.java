package com.group.controller;


import com.group.dto.request.EditProfileRequestDto;
import com.group.dto.request.SaveRequestDto;
import com.group.dto.response.GetAllResponseDto;
import com.group.dto.response.GetMinorInfoResponseDto;


import com.group.dto.request.UpdateRequestDto;

import com.group.repository.entity.Admin;
import com.group.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.group.constants.EndPoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {

    private final AdminService adminService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> createAdmin(@RequestBody SaveRequestDto dto){
        return ResponseEntity.ok(adminService.createAdmin(dto));
    }
    @GetMapping(GETALLDETAIL)
    public ResponseEntity<GetAllResponseDto>getAllDetail(@RequestParam Long id){
        return ResponseEntity.ok(adminService.getAllDetail(id));
    }
    @GetMapping(GETMINOR)
    public ResponseEntity<GetMinorInfoResponseDto> getMinorInformation(@RequestParam Long id){
        return ResponseEntity.ok(adminService.getMinorInformation(id));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody @Valid UpdateRequestDto dto){
        return ResponseEntity.ok(adminService.updateDto(dto));
    }
    @PutMapping(EDITPROFILE)
    public ResponseEntity<Boolean>editProfile(@RequestBody EditProfileRequestDto dto){
        return ResponseEntity.ok(adminService.editProfile(dto));
    }

    @DeleteMapping(DELETE+BYID)
    public ResponseEntity<Boolean> deleteAdminById(@PathVariable Long id){
        return ResponseEntity.ok(adminService.deleteAdminById(id));
    }

}
