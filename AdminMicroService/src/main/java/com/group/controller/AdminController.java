package com.group.controller;

import com.group.dto.SaveRequestDto;
import com.group.dto.UpdateRequestDto;
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

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody UpdateRequestDto dto){
        return ResponseEntity.ok(adminService.update(dto));
    }


}