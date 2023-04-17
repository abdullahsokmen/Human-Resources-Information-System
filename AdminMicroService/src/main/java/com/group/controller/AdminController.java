package com.group.controller;

import com.group.dto.SaveRequestDto;
import com.group.repository.entity.Admin;
import com.group.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.group.constants.EndPoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {

    private final AdminService adminService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody SaveRequestDto dto){
        return ResponseEntity.ok(adminService.saveDto(dto));
    }
    @GetMapping(GETALLDETAIL)
    public ResponseEntity<Admin>getAllDetail(@RequestParam Long id){
        return ResponseEntity.ok(adminService.getAllDetail(id));
    }


}
