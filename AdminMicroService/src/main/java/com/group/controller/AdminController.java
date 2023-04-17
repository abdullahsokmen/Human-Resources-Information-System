package com.group.controller;

import com.group.dto.request.SaveRequestDto;
import com.group.dto.response.GetMinorInfoResponseDto;
import com.group.repository.entity.Admin;
import com.group.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.group.constants.EndPoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {

    private final AdminService adminService;

    @PostMapping(SAVE)
    public ResponseEntity<Admin> save(@RequestBody SaveRequestDto dto){
        return ResponseEntity.ok(adminService.saveDto(dto));
    }
    @GetMapping(GETALLDETAIL)
    public ResponseEntity<Admin>getAllDetail(@RequestParam Long id){
        return ResponseEntity.ok(adminService.getAllDetail(id));
    }
    @GetMapping(GETMINOR)
    public ResponseEntity<GetMinorInfoResponseDto> getMinorInformation(@RequestParam Long id){
        return ResponseEntity.ok(adminService.getMinorInformation(id));
    }


}
